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
import com.vedantu.counselling.data.response.PlacementResponse;
import com.vedantu.counselling.data.service.mapper.PlacementMetadataMapper;
import com.vedantu.counselling.data.util.PaginationUtil;
import com.vedantu.counselling.data.view.PlacementMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
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

    public ListResponse<PlacementResponse> getPlacementData(PlacementRequest placementRequest){

        List<PlacementRecord> allRecords = placementRepository.getRecords();
        List<PlacementRecord> filteredRecords = filteredRecords(allRecords, placementRequest);
        List<PlacementRecord> sortedRecords = sortRecords(filteredRecords, placementRequest);

        return new ListResponse<>(getResponsePlacements(
                PaginationUtil.getPaginatedList(filteredRecords, sortedRecords.size(), placementRequest.getPageSize(),
                        placementRequest.getPageNumber())),
                sortedRecords.size());
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
        if(placementFilter.getColleges() != null && ! placementFilter.getColleges().isEmpty()){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getColleges().contains(r.getCollegeId()))
                    .collect(Collectors.toList());
        }
        if(placementFilter.getCollegeTypes() != null && ! placementFilter.getCollegeTypes().isEmpty()){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getCollegeTypes().contains(r.getCollegeTypeId()))
                    .collect(Collectors.toList());
        }
        if(placementFilter.getYear() != null && ! placementFilter.getYear().isEmpty()){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getYear().contains(r.getYear()))
                    .collect(Collectors.toList());
        }
        if(placementFilter.getUg_pg() != null && ! placementFilter.getUg_pg().isEmpty()){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getUg_pg().equals(r.getUgOrPg()))
                    .collect(Collectors.toList());
        }
        return filteredRecords;
    }


    private List<PlacementResponse> getResponsePlacements(List<PlacementRecord> placementList) {
        return placementList.stream().map(placement -> PlacementResponse
                        .builder()
                        .id(placement.getId())
                        .totalStudents(placement.getTotalStudents() == null ? 0:placement.getTotalStudents())
                        .studentPlacedPercent(placement.getStudentPlacedPercentage()
                                == null ? BigDecimal.ZERO:placement.getStudentPlacedPercentage())
                        .studentHigherStudyPercent(placement.getStudentHigherStudyPercentage()
                                == null ? BigDecimal.ZERO:placement.getStudentHigherStudyPercentage())
                        .averagePackage(placement.getAveragePackage()== null ? 0:placement.getAveragePackage())
                        .maxPackage(placement.getMaxPackage() == null ? 0:placement.getMaxPackage())
                        .minPackage(placement.getMinPackage() == null ? 0:placement.getMinPackage())
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
        List<College> colleges = collegeRepository.findAll();
        List<Integer> distinctYears = placementRepository.findDistinctYears();
        return PlacementMetadataMapper.mapPlacmentMetadata(collegeTypes, colleges, distinctYears, ugPg());
    }

    private List<String> ugPg(){
        List<String> ugPgList = new ArrayList<>();
        ugPgList.add("UG");
        ugPgList.add("PG");
        return ugPgList;
    }
}