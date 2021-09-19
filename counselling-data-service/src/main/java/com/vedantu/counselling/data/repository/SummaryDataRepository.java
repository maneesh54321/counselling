package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.RankType;
import com.vedantu.counselling.data.model.SummaryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryDataRepository  extends JpaRepository<SummaryData, Integer> {
}
