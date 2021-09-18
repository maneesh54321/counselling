package com.vedantu.counselling.data.request;

import com.vedantu.counselling.data.Constants;
import com.vedantu.counselling.data.model.PlacementRecord;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
public class PlacementRequest {
    private List<Integer> collegeIds; //optional
    private List<Integer> collegeTagIds; //optional
    private List<Integer> year; //optional
    private String ugOrPg; //optional

    private PlacementSortBy sortBy = PlacementSortBy.COLLEGE;
    private int pageNumber = Constants.DEFAULT_PAGE_NUMBER;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
    private SortType sortType=SortType.ASC;

    public enum PlacementSortBy{
        COLLEGE{
            @Override
            public Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getCollegeName);
            }
        },
        YEAR {
            @Override
            public Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getYear);
            }
        },
        AVG_PACKAGE {
            @Override
            public Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getAveragePackage);
            }
        },
        TOTAL_STUDENTS {
            @Override
            public Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getTotalStudents);
            }
        },
        UG_OR_PG {
            @Override
            public Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getUgOrPg);
            }
        },
        STUDENT_PLACED {
            @Override
            public Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getStudentPlacedPercentage);
            }
        },
        STUDENT_HIGHER_STUDY {
            @Override
            public Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getStudentHigherStudyPercentage);
            }
        },
        COLLEGE_TYPE {
            @Override
            public Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getCollegeType);
            }
        };

        public abstract Comparator<PlacementRecord> getComparator();
    }
}