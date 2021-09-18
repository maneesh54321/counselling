package com.vedantu.counselling.data.service.mapper;

import com.vedantu.counselling.data.model.BranchTag;
import com.vedantu.counselling.data.model.College;
import com.vedantu.counselling.data.response.view.BranchTagView;
import com.vedantu.counselling.data.response.metadata.CollegeBranchMetaData;
import com.vedantu.counselling.data.response.view.CollegeView;

import java.util.List;
import java.util.stream.Collectors;

public class CollegeBranchMetaDataMapper {

    public static CollegeBranchMetaData mapCollegeBranchMetadata(List<College> colleges, List<BranchTag> branchTags,
            List<Integer> distinctDurations ) {
        List<CollegeView> collegeViewList = colleges.parallelStream()
                .map(college -> new CollegeView(college.getId(), college.getName()))
                .collect(Collectors.toList());

        List<BranchTagView> branchTagViewList = branchTags.parallelStream()
                .map(branchTag -> new BranchTagView(branchTag.getId(), branchTag.getName()))
                .collect(Collectors.toList());

        return CollegeBranchMetaData.builder()
                .branchTags(branchTagViewList)
                .colleges(collegeViewList)
                .durations(distinctDurations)
                .build();
    }
}
