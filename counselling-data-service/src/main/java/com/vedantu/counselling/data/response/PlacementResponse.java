package com.vedantu.counselling.data.response;

import com.vedantu.counselling.data.model.CollegeBranch;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlacementResponse {
    private String collegeName;
    private String tag;
    private String ugOrPg;
    private int year;
    private int averagePackage;
    private int maxPackage;
    private int minPackage;
    private int noOfStudent;
    private int noOfPlacedStudent;
    private int noOfHigherStudyCount;

}
