package com.vedantu.counselling.data.response;

import com.vedantu.counselling.data.model.ThreeTuple;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CounsellingData {
    private int distance;
    private String college;
    private String collegeType;
    private String branch;
    private String category;
    private String gender;
    private String quota;
    private int year;
    private ThreeTuple<Integer, Integer, Integer> openCloseRank;
}
