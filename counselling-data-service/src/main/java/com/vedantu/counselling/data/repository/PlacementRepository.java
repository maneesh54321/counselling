package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.MaxClosingRankByRankType;
import com.vedantu.counselling.data.model.Placement;
import com.vedantu.counselling.data.model.PlacementRecord;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface PlacementRepository extends JpaRepository<Placement, Integer> {

    @Cacheable(value = {"placementData"})
    @Query(value = "select placement.id as id, placement.year as year, " +
            "placement.average_Package as averagePackage, placement.max_Package as maxPackage, " +
            "placement.min_Package as minPackage, placement.ug_Or_Pg as ugOrPg, placement.total_Students as totalStudents," +
            "placement.student_Placed_Percentage as studentPlacedPercentage, " +
            "placement.student_Higher_Study_Percentage as studentHigherStudyPercentage, " +
            "placement.college_id as collegeId, college.name as collegeName, college_type.id as collegeTypeId, " +
            "college_type.name as collegeType from placement placement, college college, college_type college_type " +
            "where placement.college_id = college.id and college.college_type_id = college_type.id", nativeQuery = true)
    List<PlacementRecord> getRecords();

    @Query("SELECT DISTINCT p.year FROM Placement p")
    List<Integer> findDistinctYears();
}
