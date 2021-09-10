package com.vedantu.counselling.data.service.mapper;

import com.vedantu.counselling.data.model.*;
import com.vedantu.counselling.data.view.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CounsellingDataMapper {

    public static CounsellingDataMetadata mapCounsellingDataMetadata(
            List<Category> categories, List<Gender> genders, List<Quota> quotas,
            List<CollegeType> collegeTypes, List<College> colleges, List<BranchTag> branchTags,
            List<Integer> distinctDurations, List<Integer> distinctYears, int maxDistance, List<MaxClosingRankByRankType> maxClosingRankByRankTypes
            ) {
        List<CategoryView> categoryViewList = categories.stream().map(category -> new CategoryView(category.getCategoryId(), category.getCategoryName())).collect(Collectors.toList());
        List<GenderView> genderViewList = genders.stream().map(gender -> new GenderView(gender.getGenderId(), gender.getGenderName())).collect(Collectors.toList());
        List<QuotaView> quotaViewList = quotas.stream().map(quota -> new QuotaView(quota.getQuotaId(), quota.getQuotaName())).collect(Collectors.toList());
        List<CollegeTypeView> collegeTypeViewList = collegeTypes.stream().map(collegeType -> new CollegeTypeView(collegeType.getCollegeTypeId(), collegeType.getCollegeTypeName())).collect(Collectors.toList());
        List<CollegeView> collegeViewList = colleges.stream().map(college -> new CollegeView(college.getCollegeId(), college.getCollegeName())).collect(Collectors.toList());
        List<BranchTagView> branchTagViewList = branchTags.stream().map(branchTag -> new BranchTagView(branchTag.getBranchTagId(), branchTag.getBranchTagName())).collect(Collectors.toList());
        Map<RankType, Integer> rankTypeMaxClosingRankMap = maxClosingRankByRankTypes.stream()
                .collect(Collectors.toMap(MaxClosingRankByRankType::getRankType, MaxClosingRankByRankType::getMaxClosingRank, (v1, v2) -> v1, HashMap::new));
        return new CounsellingDataMetadata(
                categoryViewList, genderViewList, quotaViewList, collegeTypeViewList, collegeViewList,
                branchTagViewList, distinctDurations, distinctYears, maxDistance, rankTypeMaxClosingRankMap
        );
    }
}
