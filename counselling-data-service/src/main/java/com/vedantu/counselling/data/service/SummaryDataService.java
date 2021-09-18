package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.repository.CounsellingDataFileRepository;
import com.vedantu.counselling.data.service.mapper.SummaryServiceDataMapper;
import com.vedantu.counselling.data.response.view.Download;
import com.vedantu.counselling.data.response.SummaryData;
import com.vedantu.counselling.data.response.view.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SummaryDataService {

    private final String description;

    private final String disclaimer;

    private static final String VIDEOS_DESCRIPTION_URI = "videos-metadata.txt";

    private final CounsellingDataFileRepository counsellingDataFileRepository;

    @Autowired
    public SummaryDataService(
            @Value("${data.summary.description}") String description,
            @Value("${data.summary.disclaimer}") String disclaimer, CounsellingDataFileRepository counsellingDataFileRepository) {
        this.description = description;
        this.disclaimer = disclaimer;
        this.counsellingDataFileRepository = counsellingDataFileRepository;
    }

    @Cacheable("summary")
    public SummaryData getSummary() {
        SummaryData summaryData = new SummaryData(description, disclaimer);
        summaryData.setVideos(getAllVideos());
        summaryData.setDownloads(fetchDownloads());
        return summaryData;
    }

    private List<Download> fetchDownloads() {
        return SummaryServiceDataMapper.mapDownloads(counsellingDataFileRepository.findAll());
    }

    private List<Video> getAllVideos() {
        try (BufferedReader videoDescriptionReader = new BufferedReader(new InputStreamReader(new ClassPathResource(VIDEOS_DESCRIPTION_URI).getInputStream()))) {
            List<Video> videos =  videoDescriptionReader.lines()
                    .map(videoDescription -> {
                        try {
                            String[] videoDescriptionTokens = videoDescription.split(";");
                            Matcher youtubeVideoIdMatcher = Pattern.compile("watch\\?v=(.*)").matcher(videoDescriptionTokens[1]);
                            if(youtubeVideoIdMatcher.find()){
                                String videoId = youtubeVideoIdMatcher.group(1);
                                return new Video(videoDescriptionTokens[0], videoId);
                            } else {
                                log.error("Invalid video url: {}", videoDescriptionTokens[1]);
                            }
                        } catch (Exception e) {
                            log.error("Invalid video description: {}", videoDescription);
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
            boolean missingVideo = videos.parallelStream().anyMatch(Objects::isNull);
            if(missingVideo){
                String errorMessage = "Error occurred while parsing some of the videos!!";
                log.error(errorMessage);
                throw new RuntimeException(errorMessage);
            }
            return videos;
        } catch (IOException e ) {
            log.error("Exception occurred while reading videos description file!!!", e);
            throw new RuntimeException("Exception occurred while reading videos description file!!!!!");
        }
    }
}
