package com.vedantu.counselling.data.service;


import com.vedantu.counselling.data.model.College;
import com.vedantu.counselling.data.model.CollegeType;
import com.vedantu.counselling.data.model.PlacementRecord;
import com.vedantu.counselling.data.repository.CollegeRepository;
import com.vedantu.counselling.data.repository.CollegeTypeRepository;
import com.vedantu.counselling.data.repository.PlacementRepository;
import com.vedantu.counselling.data.request.PlacementRequest;
import com.vedantu.counselling.data.request.SortType;
import com.vedantu.counselling.data.response.ListResponse;
import com.vedantu.counselling.data.response.PlacementData;
import com.vedantu.counselling.data.service.mapper.PlacementMetadataMapper;
import com.vedantu.counselling.data.util.PaginationUtil;
import com.vedantu.counselling.data.response.metadata.PlacementMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlacementDataService {

    private final PlacementRepository placementRepository;

    private final CollegeTypeRepository collegeTypeRepository;

    private final CollegeRepository collegeRepository;


    @Autowired
    public PlacementDataService(PlacementRepository placementRepository,
                                CollegeRepository collegeRepository,
                                CollegeTypeRepository collegeTypeRepository) {
        this.placementRepository = placementRepository;
        this.collegeRepository = collegeRepository;
        this.collegeTypeRepository = collegeTypeRepository;
    }

    public ListResponse<PlacementData> getPlacementData(PlacementRequest placementRequest){
        log.debug("Placement data request for {}", placementRequest);

        if (placementRequest == null) {
            log.info("Request is null hence no records in response");
            return new ListResponse<>(Collections.emptyList(), 0);
        }

        List<PlacementRecord> allRecords = getPlacementRepositoryRecords();
        log.debug("Total unfiltered placement records are {}", allRecords.size());

        List<PlacementRecord> filteredRecords = filteredRecords(allRecords, placementRequest);
        List<PlacementRecord> sortedRecords = sortRecords(filteredRecords, placementRequest);
        log.debug("Total sorted placement records after filtering data request are {}", sortedRecords.size());

        return new ListResponse<>(getResponsePlacements(
                PaginationUtil.getPaginatedList(filteredRecords, sortedRecords.size(), placementRequest.getPageSize(),
                        placementRequest.getPageNumber())),
                sortedRecords.size());
    }

    public List<PlacementRecord> getPlacementRepositoryRecords() {
        return placementRepository.getRecords();
    }

    private List<PlacementRecord> sortRecords(List<PlacementRecord> filteredRecords, PlacementRequest placementRequest) {
        Comparator<PlacementRecord> comparator = getRecordComparator(placementRequest);
        filteredRecords.sort(comparator);
        return filteredRecords;
    }

    private Comparator<PlacementRecord> getRecordComparator(PlacementRequest placementRequest) {
        Comparator<PlacementRecord> comparator = placementRequest.getSortBy() != null?  placementRequest.getSortBy().getComparator(): PlacementRequest.PlacementSortBy.COLLEGE.getComparator();
        if(placementRequest.getSortType() != null && placementRequest.getSortType() == SortType.DSC)
            comparator = comparator.reversed();
        return comparator;
    }

    private List<PlacementRecord> filteredRecords(List<PlacementRecord> placementRecords, PlacementRequest placementFilter){
        List<PlacementRecord> filteredRecords = new ArrayList<>(placementRecords);
        if( ! isEmpty(placementFilter.getCollegeIds())){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getCollegeIds().contains(r.getCollegeId()))
                    .collect(Collectors.toList());
        }
        if( ! isEmpty(placementFilter.getCollegeTagIds())){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getCollegeTagIds().contains(r.getCollegeTypeId()))
                    .collect(Collectors.toList());
        }
        if( ! isEmpty(placementFilter.getYear())){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getYear().contains(r.getYear()))
                    .collect(Collectors.toList());
        }
        if( ! isEmpty(placementFilter.getUgOrPg())){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getUgOrPg().equals(r.getUgOrPg()))
                    .collect(Collectors.toList());
        }
        return filteredRecords;
    }


    private List<PlacementData> getResponsePlacements(List<PlacementRecord> placementList) {
        return placementList.stream().map(placement -> PlacementData
                        .builder()
                        .id(placement.getId())
                        .totalStudents(placement.getTotalStudents() == null ? -1:placement.getTotalStudents())
                        .studentPlacedPercent(placement.getStudentPlacedPercentage()
                                == null ? BigDecimal.valueOf(-1):placement.getStudentPlacedPercentage())
                        .studentHigherStudyPercent(placement.getStudentHigherStudyPercentage()
                                == null ? BigDecimal.valueOf(-1):placement.getStudentHigherStudyPercentage())
                        .averagePackage(placement.getAveragePackage() == null ? -1:placement.getAveragePackage())
                        .maxPackage(placement.getMaxPackage() == null ? -1:placement.getMaxPackage())
                        .minPackage(placement.getMinPackage() == null ? -1:placement.getMinPackage())
                        .year(placement.getYear())
                        .college(placement.getCollegeName())
                        .collegeType(placement.getCollegeType())
                        .ugOrPg(placement.getUgOrPg())
                        .build())
                .collect(Collectors.toList());
    }

    @Cacheable(value = {"placementMetadata"})
    public PlacementMetadata getPlacementMetadata() {
        List<CollegeType> collegeTypes = collegeTypeRepository.findAll();
        log.info("Number college type for PlacementMetadata {}", collegeTypes.size());

        List<College> colleges = collegeRepository.findAll();
        log.info("Number of college for PlacementMetadata {}", colleges.size());

        List<Integer> distinctYears = placementRepository.findDistinctYears();
        log.info("Number of years for PlacementMetadata {}", distinctYears.size());

        return PlacementMetadataMapper.mapPlacementMetadata(collegeTypes, colleges, distinctYears, ugOrPg());
    }

    private List<String> ugOrPg(){
        List<String> ugPgList = new ArrayList<>();
        ugPgList.add("UG-4 Years");
        ugPgList.add("UG-5 Years");
        return ugPgList;
    }
}
