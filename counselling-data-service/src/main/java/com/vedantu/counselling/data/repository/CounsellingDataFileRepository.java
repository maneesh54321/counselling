package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.CounsellingDataFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounsellingDataFileRepository extends JpaRepository<CounsellingDataFile, Integer> {
    CounsellingDataFile findById(int id);
}
