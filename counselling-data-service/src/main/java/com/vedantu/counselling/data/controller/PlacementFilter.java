package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.PlacementRecord;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
public class PlacementFilter {
    private List<Integer> colleges;
    private List<Integer> collegeTypes;
    private List<Integer> year;
    private String ug_pg;

    private PlacementSortBy sortBy;
    private int pageNumber;
    private int pageSize;

    public enum PlacementSortBy{
        COLLEGE{
            @Override
            Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getCollegeName);
            }
        },
        YEAR {
            @Override
            Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getYear);
            }
        },
        AVG_PACKAGE {
            @Override
            Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getAveragePackage);
            }
        },
        TOTAL_STUDENTS {
            @Override
            Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getTotalStudents);
            }
        },
        UG_OR_PG {
            @Override
            Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getUgOrPg);
            }
        },
        STUDENT_PLACED {
            @Override
            Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getStudentPlacedPercentage);
            }
        },
        STUDENT_HIGHER_STUDY {
            @Override
            Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getStudentHigherStudyPercentage);
            }
        },
        COLLEGE_TYPE {
            @Override
            Comparator<PlacementRecord> getComparator() {
                return Comparator.comparing(PlacementRecord::getCollegeType);
            }
        };

        abstract Comparator<PlacementRecord> getComparator();
    }
}