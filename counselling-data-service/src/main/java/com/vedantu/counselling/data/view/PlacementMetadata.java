package com.vedantu.counselling.data.view;

import com.vedantu.counselling.data.model.RankType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class PlacementMetadata {
    private List<CollegeTypeView> collegeTypes;
    private List<CollegeView> colleges;
    private List<Integer> years;
    private List<String> ugPg;
}

