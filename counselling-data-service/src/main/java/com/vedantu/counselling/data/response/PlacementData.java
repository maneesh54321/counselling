package com.vedantu.counselling.data.response;

import com.vedantu.counselling.data.model.CollegeBranch;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PlacementData {
    private int id;
    private String college;
    private String collegeType;
    private String ugOrPg;
    private int year;
    private int averagePackage;
    private int maxPackage;
    private int minPackage;
    private BigDecimal studentPlacedPercent;
    private BigDecimal studentHigherStudyPercent;
    private int totalStudents;
}
