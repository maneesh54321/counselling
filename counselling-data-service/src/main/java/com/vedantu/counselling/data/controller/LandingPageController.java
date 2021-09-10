package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.service.CounsellingDataService;
import com.vedantu.counselling.data.service.SummaryDataService;
import com.vedantu.counselling.data.view.CityData;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.view.ResponseStatus;
import com.vedantu.counselling.data.view.SummaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counsellingapp")
public class LandingPageController {

    private CounsellingDataService counsellingDataService;

    private SummaryDataService summaryDataService;

    @Autowired
    public LandingPageController(CounsellingDataService counsellingDataService, SummaryDataService summaryDataService) {
        this.counsellingDataService = counsellingDataService;
        this.summaryDataService = summaryDataService;
    }

    @GetMapping(value = "/get-all-city", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CityData> getAllCities() {
        return new Response<>(ResponseStatus.SUCCESS, counsellingDataService.getAllCities());
    }

    @GetMapping(value = "/landing-page-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<SummaryData> getLandingPageInfo() throws Exception {
        return new Response<>(ResponseStatus.SUCCESS, summaryDataService.getSummary());
    }

}
