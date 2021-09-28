package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.exception.InvalidInputException;
import com.vedantu.counselling.data.request.CounsellingDataRequest;
import com.vedantu.counselling.data.response.CounsellingDataResponse;
import com.vedantu.counselling.data.response.Response;
import com.vedantu.counselling.data.response.ResponseStatus;
import com.vedantu.counselling.data.response.metadata.CounsellingDataMetadata;
import com.vedantu.counselling.data.service.CounsellingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/colleges/ranks")
public class RankDataController {

    private final CounsellingDataService counsellingDataService;

    private static final String COUNSELLING_RECORDS_DOWNLOAD_FILE_NAME = "counselling-records";

    @Autowired
    public RankDataController(CounsellingDataService counsellingDataService) {
        this.counsellingDataService = counsellingDataService;
    }

    @GetMapping(value = "/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CounsellingDataMetadata> getRankingScreenMetadata() {
        return new Response<>(ResponseStatus.SUCCESS, counsellingDataService.getCounsellingDataMetadata());
    }

    @PostMapping(value = "/query/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CounsellingDataResponse> getCounsellingData(@RequestBody CounsellingDataRequest counsellingDataRequest) {
        return new Response<>(ResponseStatus.SUCCESS, counsellingDataService.getCounsellingDataFor(counsellingDataRequest));
    }

    @PostMapping(value = "/query/filter/download", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResource> downloadCounsellingData(@RequestBody CounsellingDataRequest counsellingDataRequest) {

        if (Objects.isNull(counsellingDataRequest)) {
            throw new InvalidInputException("Request body can not be null!!");
        }

        CounsellingDataResponse counsellingDataResponse = this.counsellingDataService.getCounsellingDataFor(counsellingDataRequest);
        ByteArrayResource downloadFileByteArrayResource = new ByteArrayResource(
                this.counsellingDataService.convertCounsellingDataResponseToPdf(counsellingDataResponse)
        );

        return ResponseEntity.ok()
                .contentLength(downloadFileByteArrayResource.contentLength())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + COUNSELLING_RECORDS_DOWNLOAD_FILE_NAME
                )
                .body(downloadFileByteArrayResource);
    }
}
