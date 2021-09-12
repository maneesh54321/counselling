package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.model.BranchTag;
import com.vedantu.counselling.data.model.College;
import com.vedantu.counselling.data.model.CollegeBranchDbData;
import com.vedantu.counselling.data.repository.BranchRepository;
import com.vedantu.counselling.data.repository.BranchTagRepository;
import com.vedantu.counselling.data.repository.CollegeRepository;
import com.vedantu.counselling.data.request.CollegeBranchRequest;
import com.vedantu.counselling.data.request.SortType;
import com.vedantu.counselling.data.response.CollegeBranchResponse;
import com.vedantu.counselling.data.response.ListResponse;
import com.vedantu.counselling.data.service.mapper.CollegeBranchMetaDataMapper;
import com.vedantu.counselling.data.util.PaginationUtil;
import com.vedantu.counselling.data.util.SequenceGenerator;
import com.vedantu.counselling.data.view.CollegeBranchMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollegeBranchDataService {

    private final CollegeRepository collegeRepository;

    private final BranchTagRepository branchTagRepository;

    private final BranchRepository branchrepository;

    @Autowired
    public CollegeBranchDataService(CollegeRepository collegeRepository, BranchTagRepository branchTagRepository,
                                    BranchRepository branchrepository) {
        this.collegeRepository = collegeRepository;
        this.branchTagRepository = branchTagRepository;
        this.branchrepository = branchrepository;
    }

    @Cacheable(value = {"CollegeBranchMetadata"})
    public CollegeBranchMetaData getCollegeBranchMetaData() {
        List<College> colleges = collegeRepository.findAll();
        List<BranchTag> branchTags = branchTagRepository.findAll();
        List<Integer> distinctDurations = branchrepository.findDistinctDurations();
        return CollegeBranchMetaDataMapper.mapCollegeBranchMetadata(colleges, branchTags, distinctDurations);
    }

    public ListResponse<CollegeBranchResponse> getCollegeBranchDataFor(CollegeBranchRequest request) {

        if (request == null) {
            return new ListResponse<>(new LinkedList<>(), 0);
        }

        List<CollegeBranchDbData> allCollegeBranch = collegeRepository.findAllCollegeBranch();

        List<CollegeBranchResponse> finalCollegeBranchResponseData = mapCollegeBranchData(filterDataForRequest(allCollegeBranch, request));

        Comparator<CollegeBranchResponse> comparator = request.getSortType().equals(SortType.ASC) ? request.getCounsellingDataSortBy().getComparator() : request.getCounsellingDataSortBy().getComparator().reversed();
        finalCollegeBranchResponseData.sort(comparator);

        int returnCount = finalCollegeBranchResponseData.size();

        return new ListResponse<>(PaginationUtil.getPaginatedList(finalCollegeBranchResponseData, returnCount, request.getPageSize(), request.getPageNumber()), returnCount);
    }

    private List<CollegeBranchDbData> filterDataForRequest(List<CollegeBranchDbData> allCollegeBranch, CollegeBranchRequest request) {
        List<CollegeBranchDbData> filteredData = allCollegeBranch;

        if (request.getDurations() != null && !request.getDurations().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> request.getDurations().contains(entry.getDuration())).collect(Collectors.toList());
        }

        if (request.getBranchTagIds() != null && !request.getBranchTagIds().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> request.getBranchTagIds().contains(entry.getBranchTagId())).collect(Collectors.toList());
        }

        if (request.getCollegeIds() != null && !request.getCollegeIds().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> request.getCollegeIds().contains(entry.getCollegeId())).collect(Collectors.toList());
        }

        return filteredData;
    }

    private List<CollegeBranchResponse> mapCollegeBranchData(List<CollegeBranchDbData> filterDataForRequest) {
        final SequenceGenerator sequenceGenerator = new SequenceGenerator();
        return filterDataForRequest.stream().map(e ->new CollegeBranchResponse(sequenceGenerator.getNext(), e.getCollege(), e.getCollegeType(), e.getBranch(), e.getDuration())).collect(Collectors.toList());
    }
}
