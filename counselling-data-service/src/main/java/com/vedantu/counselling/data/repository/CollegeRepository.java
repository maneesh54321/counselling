package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.College;
import com.vedantu.counselling.data.model.CollegeBranchDbData;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeRepository extends JpaRepository<College, Integer> {

    @Query(value = "SELECT COL.NAME AS COLLEGE,\tCOL.ID AS COLLEGEID, CT.NAME AS COLLEGETYPE, CT.ID AS COLLEGETYPEID, B.NAME AS BRANCH, BT.ID AS BRANCHTAGID, B.DURATION AS DURATION \n" +
            "FROM COLLEGE_BRANCH CB INNER JOIN BRANCH B ON CB.BRANCH_ID = B.ID INNER JOIN BRANCH_TAG BT ON B.BRANCH_TAG_ID = BT.ID INNER JOIN COLLEGE COL ON CB.COLLEGE_ID = COL.ID INNER JOIN COLLEGE_TYPE CT ON COL.COLLEGE_TYPE_ID = CT.ID", nativeQuery = true)
    @Cacheable(value = "CollegeBranchData")
    List<CollegeBranchDbData> findAllCollegeBranch();
}
