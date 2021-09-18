package com.vedantu.counselling.data.response.metadata;

import com.vedantu.counselling.data.response.view.BranchTagView;
import com.vedantu.counselling.data.response.view.CollegeView;
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
