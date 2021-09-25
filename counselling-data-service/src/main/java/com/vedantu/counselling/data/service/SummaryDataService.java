package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.model.DisclaimerData;
import com.vedantu.counselling.data.repository.CounsellingDataFileRepository;
import com.vedantu.counselling.data.repository.DisclaimerRepository;
import com.vedantu.counselling.data.repository.SummaryDataRepository;
import com.vedantu.counselling.data.response.view.DisclaimerView;
import com.vedantu.counselling.data.service.mapper.SummaryServiceDataMapper;
import com.vedantu.counselling.data.response.view.Download;
import com.vedantu.counselling.data.response.SummaryData;
import com.vedantu.counselling.data.response.view.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SummaryDataService {

    private final CounsellingDataFileRepository counsellingDataFileRepository;
    private final VideoService videoService;
    private final SummaryDataRepository summaryDataRepository;
    private final DisclaimerRepository disclaimerRepository;

    @Autowired
    public SummaryDataService(CounsellingDataFileRepository counsellingDataFileRepository,
                              VideoService videoService,
                              SummaryDataRepository summaryDataRepository, DisclaimerRepository disclaimerRepository) {
        this.counsellingDataFileRepository = counsellingDataFileRepository;
        this.videoService = videoService;
        this.summaryDataRepository = summaryDataRepository;
        this.disclaimerRepository = disclaimerRepository;
    }

    @Cacheable("summary")
    public SummaryData getSummary() {
        com.vedantu.counselling.data.model.SummaryData summaryDataDb = summaryDataRepository.findAll().stream().findFirst()
                .orElseThrow(()-> new RuntimeException("No summary data set"));

        SummaryData summaryData = new SummaryData(summaryDataDb.getDescription());
        summaryData.setDisclaimer(getAllDisclaimer());
        summaryData.setVideos(getAllVideos());
        summaryData.setDownloads(fetchDownloads());
        return summaryData;
    }

    private List<DisclaimerView> getAllDisclaimer() {
       return SummaryServiceDataMapper.mapDisclaimer(disclaimerRepository.findAll());
    }

    private List<Download> fetchDownloads() {
        return SummaryServiceDataMapper.mapDownloads(counsellingDataFileRepository.findAll());
    }

    private List<Video> getAllVideos() {
        log.debug("Processing All videos request");
        return videoService.getAllActive().stream()
                .map(dbv -> Video.builder().name(dbv.getName()).uri(dbv.getUri()).build())
                .collect(Collectors.toList());
    }

    @Transactional
    public com.vedantu.counselling.data.model.SummaryData createNewSummary(String description) {
        summaryDataRepository.deleteAll();
        com.vedantu.counselling.data.model.SummaryData summaryData = new com.vedantu.counselling.data.model.SummaryData();
        summaryData.setDescription(description);
        return summaryDataRepository.save(summaryData);
    }

    @Transactional
    public DisclaimerData updateDisclaimer(String type, String content) {
        DisclaimerData disclaimerData = disclaimerRepository.findByType(type);
        if(disclaimerData == null) {
            disclaimerData = new com.vedantu.counselling.data.model.DisclaimerData();
            disclaimerData.setType(type);
            disclaimerData.setContent(content);
        }
        else {
            disclaimerData.setContent(content);
        }
        return disclaimerRepository.save(disclaimerData);
    }
}
