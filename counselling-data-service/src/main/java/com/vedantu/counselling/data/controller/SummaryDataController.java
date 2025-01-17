package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.exception.InvalidInputException;
import com.vedantu.counselling.data.response.Response;
import com.vedantu.counselling.data.response.ResponseStatus;
import com.vedantu.counselling.data.response.SummaryData;
import com.vedantu.counselling.data.response.view.CityData;
import com.vedantu.counselling.data.response.view.DownloadedFile;
import com.vedantu.counselling.data.service.AccessTrackerService;
import com.vedantu.counselling.data.service.CounsellingDataService;
import com.vedantu.counselling.data.service.DownloadService;
import com.vedantu.counselling.data.service.SummaryDataService;
import com.vedantu.counselling.data.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/landing-page")
public class SummaryDataController {

    private final CounsellingDataService counsellingDataService;

    private final SummaryDataService summaryDataService;

    private final DownloadService downloadService;

    private final AccessTrackerService accessTrackerService;

    @Autowired
    public SummaryDataController(
            CounsellingDataService counsellingDataService,
            SummaryDataService summaryDataService,
            DownloadService downloadService,
            AccessTrackerService accessTrackerService) {
        this.counsellingDataService = counsellingDataService;
        this.summaryDataService = summaryDataService;
        this.downloadService = downloadService;
        this.accessTrackerService = accessTrackerService;
    }

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CityData> getAllCities() {
        accessTrackerService.noteNewAccess();
        return new Response<>(ResponseStatus.SUCCESS, counsellingDataService.getAllCities());
    }

    @GetMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<SummaryData> getLandingPageInfo() {
        return new Response<>(ResponseStatus.SUCCESS, summaryDataService.getSummary());
    }

    @GetMapping(value = "/files/download/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("fileId") Integer fileId)
            throws InvalidInputException {
        if (Objects.isNull(fileId)) {
            throw new InvalidInputException("File id can not be null!!");
        }

        DownloadedFile downloadedFile = this.downloadService.downloadFile(fileId);
        ByteArrayResource downloadFileByteArrayResource = new ByteArrayResource(downloadedFile.getBytes());

        return ResponseEntity.ok()
                .contentLength(downloadFileByteArrayResource.contentLength())
                .header(HttpHeaders.CONTENT_TYPE, Utils.getContentTypeFromFileName(downloadedFile.getName()))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + downloadedFile.getName()
                )
                .body(downloadFileByteArrayResource);
    }

}
