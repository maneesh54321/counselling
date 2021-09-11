package com.vedantu.counselling.data.model;

import java.math.BigDecimal;

public interface PlacementRecord {
     int getId();
     int getYear();
     Integer getAveragePackage();
     Integer getMaxPackage();
     Integer getMinPackage();
     String getUgOrPg();
     Integer getTotalStudents();
     BigDecimal getStudentPlacedPercentage();
     BigDecimal getStudentHigherStudyPercentage();
     int getCollegeId();
     int getCollegeTypeId();
     String getCollegeName();
     String getCollegeType();
}
