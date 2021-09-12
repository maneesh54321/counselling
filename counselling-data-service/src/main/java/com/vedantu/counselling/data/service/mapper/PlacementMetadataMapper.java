package com.vedantu.counselling.data.service.mapper;

import com.vedantu.counselling.data.model.College;
import com.vedantu.counselling.data.model.CollegeType;
import com.vedantu.counselling.data.view.CollegeTypeView;
import com.vedantu.counselling.data.view.CollegeView;
import com.vedantu.counselling.data.view.CounsellingDataMetadata;
import com.vedantu.counselling.data.view.PlacementMetadata;

import java.util.List;
import java.util.stream.Collectors;

public class PlacementMetadataMapper {

    public static PlacementMetadata mapPlacmentMetadata(List<CollegeType> collegeTypes, List<College> colleges,
                                                        List<Integer> distinctYears, List<String> ugPg) {
        List<CollegeTypeView> collegeTypeViewList = collegeTypes.parallelStream()
                .map(collegeType -> new CollegeTypeView(collegeType.getId(), collegeType.getName()))
                .collect(Collectors.toList());

        List<CollegeView> collegeViewList = colleges.parallelStream()
                .map(college -> new CollegeView(college.getId(), college.getName()))
                .collect(Collectors.toList());

        return PlacementMetadata.builder()
                .colleges(collegeViewList)
                .collegeTypes(collegeTypeViewList)
                .years(distinctYears)
                .ugPg(ugPg)
                .build();


    }
}
