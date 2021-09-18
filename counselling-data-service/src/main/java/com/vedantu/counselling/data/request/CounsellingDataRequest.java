package com.vedantu.counselling.data.request;

import com.vedantu.counselling.data.Constants;
import com.vedantu.counselling.data.response.CounsellingData;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class CounsellingDataRequest {
    private List<Integer> collegeIds;
    private List<Integer> collegeTagIds;
    private List<Integer> branchTagIds;
    private List<Integer> durations;
    private List<Integer> years;
    private int categoryId;
    private int genderId;
    private int quotaId;
    private int cityId;
    private int distanceLimit;
    private int mainsCRStart;
    private int mainsCREnd;
    private int advanceCRStart;
    private int advanceCREnd;
    private int bArchCRStart;
    private int bArchCREnd;

    private CounsellingDataSortBy counsellingDataSortBy;
    private SortType sortType;
    private int pageNumber = Constants.DEFAULT_PAGE_NUMBER;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    public enum CounsellingDataSortBy {
        COLLEGE {
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getCollege);
            }
        },
        DISTANCE{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getDistance);
            }
        },
        COLLEGE_TYPE{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getCollegeType);
            }
        },
        BRANCH{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getBranch);
            }
        },
        GENDER{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getBranch);
            }
        },
        CATEGORY{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getCategory);
            }
        },
        QUOTA{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getQuota);
            }
        },
        YEAR{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getYear);
            }
        },
        ADVANCE_RANK_OPEN{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getOpeningRankAdvance);
            }
        },
        ADVANCE_RANK_CLOSE{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getClosingRankAdvance);
            }
        },
        MAIN_RANK_OPEN{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getOpeningRankMains);
            }
        },
        MAIN_RANK_CLOSE{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getClosingRankMains);
            }
        },
        BARCH_RANK_OPEN{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getOpeningRankBArch);
            }
        },
        BARCH_RANK_CLOSE{
            @Override
            public Comparator<CounsellingData> getComparator() {
                return Comparator.comparing(CounsellingData::getClosingRankBArch);
            }
        };

        public abstract Comparator<CounsellingData> getComparator();
    }
}
