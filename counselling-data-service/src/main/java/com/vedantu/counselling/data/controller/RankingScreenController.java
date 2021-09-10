package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.*;
import com.vedantu.counselling.data.request.CounsellingDataRequest;
import com.vedantu.counselling.data.response.CounsellingData;
import com.vedantu.counselling.data.response.CounsellingDataMetadata;
import com.vedantu.counselling.data.response.CounsellingDataResponse;
import com.vedantu.counselling.data.response.Response;
import com.vedantu.counselling.data.service.CounsellingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin
@RequestMapping("/counsellingapp")
public class RankingScreenController {

    private final CounsellingDataService counsellingDataService;

    @Autowired
    public RankingScreenController(CounsellingDataService counsellingDataService) {
        this.counsellingDataService = counsellingDataService;
    }

    @GetMapping(value = "/rank-screen-metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CounsellingDataMetadata> getRankingScreenMetadata() {
        return new Response<>("Success", counsellingDataService.getCounsellingDataMetadata());
    }

    @PostMapping(value = "/rank-screen-get-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CounsellingDataResponse> getCounsellingDataFor(@RequestBody CounsellingDataRequest counsellingDataRequest) {
        return new Response<>("Success", getDummyData());
    }



    private CounsellingDataResponse getDummyData() {
        List<CounsellingData> list = new LinkedList<>();
        list.add(new CounsellingData(100, "IIIT Hyderabad", "IIIT", "IT", "SC", "Army", "Female-Only", 2020, new ThreeTuple<>(2, 1, 500)));
        list.add(new CounsellingData(120, "IIT Mumbai", "IIT", "IT", "SC", "Army", "Female-Only", 2020, new ThreeTuple<>(1, 1, 500)));
        list.add(new CounsellingData(130, "NIT Lucknow", "NIT", "IT", "SC", "Army", "Female-Only", 2020, new ThreeTuple<>(2, 1, 500)));
        return new CounsellingDataResponse(130, list);

    }
}
