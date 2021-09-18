package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.AccessTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccessTrackerRepository extends JpaRepository<AccessTracker, Integer> {

    @Query("SELECT max(a.id) FROM AccessTracker a")
    Integer getAccessCount();

}
