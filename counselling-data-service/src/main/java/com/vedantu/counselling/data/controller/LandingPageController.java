package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.service.CounsellingDataService;
import com.vedantu.counselling.data.service.DownloadService;
import com.vedantu.counselling.data.service.SummaryDataService;
import com.vedantu.counselling.data.view.CityData;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.view.ResponseStatus;
import com.vedantu.counselling.data.view.SummaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counsellingapp")
public class LandingPageController {

    private final CounsellingDataService counsellingDataService;

    private final SummaryDataService summaryDataService;

    private final DownloadService downloadService;

    @Autowired
    public LandingPageController(CounsellingDataService counsellingDataService, SummaryDataService summaryDataService, DownloadService downloadService) {
        this.counsellingDataService = counsellingDataService;
        this.summaryDataService = summaryDataService;
        this.downloadService = downloadService;
    }

    @GetMapping(value = "/get-all-city", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CityData> getAllCities() {
        return new Response<>(ResponseStatus.SUCCESS, counsellingDataService.getAllCities());
    }

    @GetMapping(value = "/landing-page-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<SummaryData> getLandingPageInfo() throws Exception {
        return new Response<>(ResponseStatus.SUCCESS, summaryDataService.getSummary());
    }

    @GetMapping(value = "/download/{fileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) throws Exception {
        byte[] downloadFile = this.downloadService.downloadFile(fileName);
        return ResponseEntity.ok()
                .contentLength(downloadFile.length)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf")
                             .body(downloadFile);
    }

}
