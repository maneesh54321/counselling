package com.vedantu.counselling.data.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CounsellingData {
    private int id;
    private int distance;
    private String college;
    private String collegeType;
    private String branch;
    private String category;
    private String gender;
    private String quota;
    private int year;
    private int openingRankAdvance;
    private int openingRankMains;
    private int openingRankBArch;
    private int closingRankAdvance;
    private int closingRankMains;
    private int closingRankBArch;
}
