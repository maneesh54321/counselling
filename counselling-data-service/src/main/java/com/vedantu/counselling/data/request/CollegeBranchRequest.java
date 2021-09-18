package com.vedantu.counselling.data.request;

import com.vedantu.counselling.data.Constants;
import com.vedantu.counselling.data.response.CollegeBranchResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class CollegeBranchRequest {

    private List<Integer> collegeIds;
    private List<Integer> branchTagIds;
    private List<Integer> durations;

    private CollegeBranchSortBy collegeBranchSortBy;
    private SortType sortType;
    private int pageNumber = Constants.DEFAULT_PAGE_NUMBER;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

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
}
