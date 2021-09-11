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
        List<CategoryView> categoryViewList = categories.parallelStream()
                .map(category -> new CategoryView(category.getId(), category.getName()))
                .collect(Collectors.toList());

        List<GenderView> genderViewList = genders.parallelStream()
                .map(gender -> new GenderView(gender.getGenderId(), gender.getName()))
                .collect(Collectors.toList());

        List<QuotaView> quotaViewList = quotas.parallelStream()
                .map(quota -> new QuotaView(quota.getQuotaId(), quota.getName()))
                .collect(Collectors.toList());

        List<CollegeTypeView> collegeTypeViewList = collegeTypes.parallelStream()
                .map(collegeType -> new CollegeTypeView(collegeType.getId(), collegeType.getName()))
                .collect(Collectors.toList());

        List<CollegeView> collegeViewList = colleges.parallelStream()
                .map(college -> new CollegeView(college.getId(), college.getName()))
                .collect(Collectors.toList());

        List<BranchTagView> branchTagViewList = branchTags.parallelStream()
                .map(branchTag -> new BranchTagView(branchTag.getId(), branchTag.getName()))
                .collect(Collectors.toList());

        Map<RankType, Integer> rankTypeMaxClosingRankMap = maxClosingRankByRankTypes.parallelStream()
                .collect(Collectors.toMap(MaxClosingRankByRankType::getRankType, MaxClosingRankByRankType::getMaxClosingRank, (v1, v2) -> v1, HashMap::new));

        return CounsellingDataMetadata.builder()
                .categories(categoryViewList)
                .genders(genderViewList)
                .quotas(quotaViewList)
                .branchTags(branchTagViewList)
                .colleges(collegeViewList)
                .collegeTypes(collegeTypeViewList)
                .durations(distinctDurations)
                .years(distinctYears)
                .maxDistance(maxDistance)
                .maxRanks(rankTypeMaxClosingRankMap)
                .build();
    }

    public static CityData mapCityData(List<City> cities) {
        final CityView defaultCity = new CityView();
        List<CityView> cityViewList = cities.parallelStream()
                .map(city -> {
                    if (city.isDefault()) {
                        defaultCity.setId(city.getCityId());
                        defaultCity.setDisplayName(city.getDisplayName());
                        defaultCity.setFullName(city.getFullName());
                    }
                    return new CityView(city.getCityId(), city.getDisplayName(), city.getFullName());
                })
                .collect(Collectors.toList());

        return new CityData(defaultCity, cityViewList);
    }
}
