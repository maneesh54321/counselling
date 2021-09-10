package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.Placement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlacementRepository extends JpaRepository<Placement, Integer> {
}
