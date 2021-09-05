package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    @Query("SELECT DISTINCT b.duration FROM Branch b")
    List<Integer> findDistinctDurations();
}
