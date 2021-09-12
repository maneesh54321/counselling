package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.exception.InvalidInputException;
import com.vedantu.counselling.data.service.CounsellingDataService;
import com.vedantu.counselling.data.service.DownloadService;
import com.vedantu.counselling.data.service.SummaryDataService;
import com.vedantu.counselling.data.view.ResponseStatus;
import com.vedantu.counselling.data.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/counsellingapp")
public class LandingPageController {

    private final CounsellingDataService counsellingDataService;

    private final SummaryDataService summaryDataService;

    private final DownloadService downloadService;

    private static final MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();

    @Autowired
    public LandingPageController(
            CounsellingDataService counsellingDataService,
            SummaryDataService summaryDataService,
            DownloadService downloadService
    ) {
        this.counsellingDataService = counsellingDataService;
        this.summaryDataService = summaryDataService;
        this.downloadService = downloadService;
    }

    @GetMapping(value = "/get-all-city", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CityData> getAllCities() {
        return new Response<>(ResponseStatus.SUCCESS, counsellingDataService.getAllCities());
    }

    @GetMapping(value = "/landing-page-info", produces = MediaType.APPLICATION_JSON_VALUE)
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
                .header(HttpHeaders.CONTENT_TYPE, getContentType(downloadedFile))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + downloadedFile.getName()
                )
                .body(downloadFileByteArrayResource);
    }

    private String getContentType(DownloadedFile downloadedFile) {
        String type = StringUtils.getFilenameExtension(downloadedFile.getName());
        String contentType = fileTypeMap.getContentType(type);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }

    @PostMapping("/files/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("description") String description
    ) throws InvalidInputException {
        downloadService.uploadFile(description, file);
        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully!");
    }

}
