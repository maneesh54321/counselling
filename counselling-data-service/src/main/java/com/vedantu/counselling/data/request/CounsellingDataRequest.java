package com.vedantu.counselling.data.request;

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
    private int mainsCRStart;
    private int mainsCREnd;
    private int advanceCRStart;
    private int advanceCREnd;
    private int bArchCRStart;
    private int bArchCREnd;
    private CounsellingDataSortBy counsellingDataSortBy;
    private SortType sortType;
}
