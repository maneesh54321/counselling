package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, Integer> {

    @Query("SELECT DISTINCT r.year FROM Rank r")
    List<Integer> findDistinctYears();
}
