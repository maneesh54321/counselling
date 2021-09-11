package com.vedantu.counselling.data.request;

import com.vedantu.counselling.data.response.CounsellingData;

import java.util.Comparator;

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
            return Comparator.comparing(c1 -> c1.getOpenCloseRank().getSecond());
        }
    },
    ADVANCE_RANK_CLOSE{
        @Override
        public Comparator<CounsellingData> getComparator() {
            return Comparator.comparing(c1 -> c1.getOpenCloseRank().getThird());
        }
    },
    MAIN_RANK_OPEN{
        @Override
        public Comparator<CounsellingData> getComparator() {
            return Comparator.comparing(c1 -> c1.getOpenCloseRank().getSecond());
        }
    },
    MAIN_RANK_CLOSE{
        @Override
        public Comparator<CounsellingData> getComparator() {
            return Comparator.comparing(c1 -> c1.getOpenCloseRank().getThird());
        }
    },
    ARCH_RANK_OPEN{
        @Override
        public Comparator<CounsellingData> getComparator() {
            return Comparator.comparing(c1 -> c1.getOpenCloseRank().getSecond());
        }
    },
    ARCH_RANK_CLOSE{
        @Override
        public Comparator<CounsellingData> getComparator() {
            return Comparator.comparing(c1 -> c1.getOpenCloseRank().getThird());
        }
    };

    public abstract Comparator<CounsellingData> getComparator();
}
