package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.CounsellingDbData;
import com.vedantu.counselling.data.model.MaxClosingRankByRankType;
import com.vedantu.counselling.data.model.Rank;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, Integer> {

    @Query("SELECT DISTINCT r.year FROM Rank r")
    List<Integer> findDistinctYears();

    @Query("SELECT rt as rankType, MAX(r.closingRank) as maxClosingRank from Rank r JOIN r.rankType rt GROUP BY rt")
    List<MaxClosingRankByRankType> findMaxClosingRankByRankType();

    @Query(value = "SELECT COL.NAME AS college, COL.ID as collegeId, CT.NAME as collegeType, CT.ID as collegeTypeId, B.NAME as branch, BT.ID as branchTagId, CAT.NAME as category, CAT.ID as categoryId, \n" +
            "GEN.NAME as gender, GEN.ID as genderId, Q.NAME quota, Q.ID as quotaId, R.YEAR as year, R.RANK_TYPE_ID as rankTypeId, R.OPEN_RANK as openRank, R.CLOSING_RANK as closeRank, B.DURATION AS DURATION \n" +
            "FROM RANK R\n" +
            "INNER JOIN CATEGORY CAT ON R.CATEGORY_ID = CAT.ID\n" +
            "INNER JOIN GENDER GEN ON R.GENDER_ID = GEN.ID\n" +
            "INNER JOIN QUOTA Q ON R.QUOTA_ID = Q.ID\n" +
            "INNER JOIN COLLEGE_BRANCH CB ON R.COLLEGE_BRANCH_ID = CB.COLLEGE_BRANCH_ID\n" +
            "INNER JOIN BRANCH B ON CB.BRANCH_ID = B.ID\n" +
            "INNER JOIN BRANCH_TAG BT ON B.BRANCH_TAG_ID = BT.ID\n" +
            "INNER JOIN COLLEGE COL ON CB.COLLEGE_ID = COL.ID\n" +
            "INNER JOIN COLLEGE_TYPE CT ON COL.COLLEGE_TYPE_ID = CT.ID", nativeQuery = true)
    @Cacheable(value =  {"CounsellingDbDataAll"})
    List<CounsellingDbData> findCounsellingDbData();
}
