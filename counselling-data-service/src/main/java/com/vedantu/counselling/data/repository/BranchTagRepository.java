package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.BranchTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchTagRepository extends JpaRepository<BranchTag, Integer> {
}
