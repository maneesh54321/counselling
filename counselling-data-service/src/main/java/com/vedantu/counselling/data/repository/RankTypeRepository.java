package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.RankType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankTypeRepository extends JpaRepository<RankType, Integer> {
}
