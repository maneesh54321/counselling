package com.vedantu.counselling.data.response;

import com.vedantu.counselling.data.model.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CounsellingDataMetadata {
    private List<Category> categories;
    private List<Gender> genders;
    private List<Quota> quotas;
    private List<CollegeType> collegeTypes;
    private List<College> colleges;
    private List<BranchTag> branchTags;
    private List<Integer> durations;
    private List<Integer> years;
    private int maxDistance;
    private Map<RankType, Integer> maxRanks;
}
