package com.vedantu.counselling.data.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CollegeBranchResponse {
    int id;
    String collegeName;
    String collegeType;
    String branchName;
    int duration;
}
