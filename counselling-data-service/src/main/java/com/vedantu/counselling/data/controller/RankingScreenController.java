package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.ThreeTuple;
import com.vedantu.counselling.data.request.CounsellingDataRequest;
import com.vedantu.counselling.data.response.CounsellingData;
import com.vedantu.counselling.data.response.CounsellingDataResponse;
import com.vedantu.counselling.data.util.PathConstants;
import com.vedantu.counselling.data.view.CounsellingDataMetadata;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.service.CounsellingDataService;
import com.vedantu.counselling.data.view.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(PathConstants.COUNSELLINGAPP)
public class RankingScreenController {

    private final CounsellingDataService counsellingDataService;

    @Autowired
    public RankingScreenController(CounsellingDataService counsellingDataService) {
        this.counsellingDataService = counsellingDataService;
    }

    @GetMapping(value = "/rank-screen-metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CounsellingDataMetadata> getRankingScreenMetadata() {
        return new Response<>(ResponseStatus.SUCCESS, counsellingDataService.getCounsellingDataMetadata());
    }

    @PostMapping(value = "/rank-screen-get-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CounsellingDataResponse> getCounsellingDataFor(@RequestBody CounsellingDataRequest counsellingDataRequest) {
        return new Response<>(ResponseStatus.SUCCESS, counsellingDataService.getCounsellingDataFor(counsellingDataRequest));
    }
}
