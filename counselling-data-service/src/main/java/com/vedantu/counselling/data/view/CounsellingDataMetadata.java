package com.vedantu.counselling.data.view;

import com.vedantu.counselling.data.model.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CounsellingDataMetadata {
    private List<CategoryView> categories;
    private List<GenderView> genders;
    private List<QuotaView> quotas;
    private List<CollegeTypeView> collegeTypes;
    private List<CollegeView> colleges;
    private List<BranchTagView> branchTags;
    private List<Integer> durations;
    private List<Integer> years;
    private int maxDistance;
    private Map<RankType, Integer> maxRanks;
}
