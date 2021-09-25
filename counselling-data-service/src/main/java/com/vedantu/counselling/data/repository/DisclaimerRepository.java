package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.DisclaimerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisclaimerRepository extends JpaRepository<DisclaimerData, Integer> {
    DisclaimerData findByType(String type);
}
