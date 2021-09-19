package com.vedantu.counselling.data.repository;

import com.vedantu.counselling.data.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VideoRepository  extends JpaRepository<Video, Integer> {

    Set<Video> findByActive(boolean b);

}
