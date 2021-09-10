package com.vedantu.counselling.data.request;

import com.vedantu.counselling.data.model.TwoTuple;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CounsellingDataRequest {
    private int categoryId;
    private int genderId;
    private int quotaId;
    private int cityId;
    private int distanceLimit;
    private int pageNumber;
    private int pageSize;
    private List<Integer> collegeIds;
    private List<Integer> collegeTagIds;
    private List<Integer> branchTagIds;
    private List<Integer> durations;
    private List<Integer> years;
    private List<TwoTuple<Integer, Integer>> closingRanks;
    private SortBy sortBy;
    private SortType sortType;
}
