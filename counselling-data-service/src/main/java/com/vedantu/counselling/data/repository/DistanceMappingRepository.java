package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.DistanceMapping;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistanceMappingRepository extends JpaRepository<DistanceMapping, Integer>{

    @Query("select dm from DistanceMapping dm join dm.city c where c.cityId = :cityId ")
    @Cacheable(value = "DistanceByCityID")
    List<DistanceMapping> findByCityId(Integer cityId);
}
