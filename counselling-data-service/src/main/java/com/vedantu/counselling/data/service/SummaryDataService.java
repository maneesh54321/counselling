package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.view.SummaryData;
import com.vedantu.counselling.data.view.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        try (Stream<String> videosDescriptionStream = Files.lines(Paths.get(ClassLoader.getSystemResource(VIDEOS_DESCRIPTION_URI).toURI()))) {
            List<Video> videos = videosDescriptionStream
                    .map(videoDescription -> {
                        String[] videoDescriptionTokens = videoDescription.split(";");
                        return new Video(videoDescriptionTokens[0], videoDescriptionTokens[1]);
                    })
                    .collect(Collectors.toList());
            summaryData.setVideos(videos);
        } catch (IOException | URISyntaxException e ) {
            log.error("Exception occurred while reading videos description file!!!", e);
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Invalid videos description file!!!", e);
            throw e;
        }
        return summaryData;
    }
}
