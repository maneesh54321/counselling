package com.vedantu.counselling.data.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CollegeBranchRequest {
    private List<Integer> collegeIds;
    private List<Integer> branchTagIds;
    private List<Integer> durations;
    private CollegeBranchSortBy counsellingDataSortBy;
    private SortType sortType;
    private int pageNumber;
    private int pageSize;
}
