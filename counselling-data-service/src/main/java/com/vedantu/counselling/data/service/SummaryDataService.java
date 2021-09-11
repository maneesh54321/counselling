package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.view.Download;
import com.vedantu.counselling.data.view.SummaryData;
import com.vedantu.counselling.data.view.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    @Autowired
    public SummaryDataService(
            @Value("${data.summary.description}") String description,
            @Value("${data.summary.disclaimer}") String disclaimer) {
        this.description = description;
        this.disclaimer = disclaimer;
    }

    @Cacheable("summary")
    public SummaryData getSummary() throws Exception {
        SummaryData summaryData = new SummaryData(description, disclaimer);
        summaryData.setVideos(getAllVideos());
        summaryData.setDownloads(fetchDownloads());
        return summaryData;
    }

    private List<Download> fetchDownloads() {
        Download download1 = new Download(0, "file 01 description", "file01");
        Download download2 = new Download(1, "file 02 description", "file02");
        Download download3 = new Download(2, "file 03 description", "file03");
        Download download4 = new Download(3, "file 04 description", "file04");
        Download download5 = new Download(4, "file 05 description", "file05");
        return Arrays.asList(download1, download2, download3, download4, download5);
    }

    private List<Video> getAllVideos() throws Exception {
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
                throw new Exception("Error occurred while parsing some of the videos!!");
            }
            return videos;
        } catch (IOException e ) {
            log.error("Exception occurred while reading videos description file!!!", e);
            throw e;
        }
    }
}
