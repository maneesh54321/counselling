package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Integer> {
}
