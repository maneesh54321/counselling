package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.exception.InvalidInputException;
import com.vedantu.counselling.data.model.Video;
import com.vedantu.counselling.data.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    public Video createNewVideo(String name, String uri) {
        Video video = new Video();
        video.setName(name);
        video.setUri(uri);
        video.setActive(true);
        return videoRepository.save(video);
    }

    public Video setActive(int id, boolean active) {
        Optional<Video> dbVideo = videoRepository.findById(id);
        dbVideo.orElseThrow( ()-> new InvalidInputException("Video does not exist"));
        dbVideo.get().setActive(active);
        return videoRepository.save(dbVideo.get());
    }

    public Set<Video> getAllActive() {
        return videoRepository.findByActive(true);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }
}
