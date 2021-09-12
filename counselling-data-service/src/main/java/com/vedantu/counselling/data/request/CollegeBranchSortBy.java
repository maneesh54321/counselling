package com.vedantu.counselling.data.request;

import com.vedantu.counselling.data.response.CollegeBranchResponse;

import java.util.Comparator;

public enum CollegeBranchSortBy {
    COLLEGE {
        @Override
        public Comparator<CollegeBranchResponse> getComparator() {
            return Comparator.comparing(CollegeBranchResponse::getCollegeName);
        }
    },
    COLLEGE_TYPE{
        @Override
        public Comparator<CollegeBranchResponse> getComparator() {
            return Comparator.comparing(CollegeBranchResponse::getCollegeType);
        }
    },
    BRANCH{
        @Override
        public Comparator<CollegeBranchResponse> getComparator() {
            return Comparator.comparing(CollegeBranchResponse::getBranchName);
        }
    },
    DURATION {
        @Override
        public Comparator<CollegeBranchResponse> getComparator() {
            return Comparator.comparing(CollegeBranchResponse::getDuration);
        }
    };

    public abstract Comparator<CollegeBranchResponse> getComparator();

}
