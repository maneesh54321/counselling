package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.CollegeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeTypeRepository extends JpaRepository<CollegeType, Integer> {
}
