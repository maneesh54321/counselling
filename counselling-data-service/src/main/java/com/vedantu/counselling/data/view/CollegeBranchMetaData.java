package com.vedantu.counselling.data.view;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CollegeBranchMetaData {
    private List<CollegeView> colleges;
    private List<BranchTagView> branchTags;
    private List<Integer> durations;
}
