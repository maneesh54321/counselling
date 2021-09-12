package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.PlacementRecord;
import com.vedantu.counselling.data.repository.PlacementRepository;
import com.vedantu.counselling.data.request.PlacementRequest;
import com.vedantu.counselling.data.response.ListResponse;
import com.vedantu.counselling.data.response.PlacementResponse;
import com.vedantu.counselling.data.service.CounsellingDataService;
import com.vedantu.counselling.data.service.PlacementDataService;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.view.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/counsellingapp")
@CrossOrigin
public class PlacementViewController {

    private final PlacementDataService placementDataService;

    @Autowired
    public PlacementViewController(PlacementDataService placementDataService) {
        this.placementDataService = placementDataService;
    }

    @PostMapping(value = "/placements", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<ListResponse<PlacementResponse>> getPlacements(@RequestBody PlacementRequest placementRequest) {
        return new Response<>(ResponseStatus.SUCCESS, placementDataService.getPlacementData(placementRequest));
    }

}
