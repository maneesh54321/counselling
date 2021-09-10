package com.vedantu.counselling.data.service;

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
import java.util.List;
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
        try (BufferedReader videoDescriptionReader = new BufferedReader(new InputStreamReader(new ClassPathResource(VIDEOS_DESCRIPTION_URI).getInputStream()))) {
            List<Video> videos = videoDescriptionReader.lines()
                    .map(videoDescription -> {
                        String[] videoDescriptionTokens = videoDescription.split(";");
                        return new Video(videoDescriptionTokens[0], videoDescriptionTokens[1]);
                    })
                    .collect(Collectors.toList());
            summaryData.setVideos(videos);
        } catch (IOException e ) {
            log.error("Exception occurred while reading videos description file!!!", e);
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Invalid videos description file!!!", e);
            throw e;
        }
        return summaryData;
    }
}
