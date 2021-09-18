package com.vedantu.counselling.data.response.metadata;

import com.vedantu.counselling.data.response.view.CollegeTypeView;
import com.vedantu.counselling.data.response.view.CollegeView;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlacementMetadata {
    private List<CollegeTypeView> collegeTypes;
    private List<CollegeView> colleges;
    private List<Integer> years;
    private List<String> ugOrPg;
}

