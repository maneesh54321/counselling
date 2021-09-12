package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.PlacementRecord;
import com.vedantu.counselling.data.repository.PlacementRepository;
import com.vedantu.counselling.data.request.PlacementRequest;
import com.vedantu.counselling.data.response.ListResponse;
import com.vedantu.counselling.data.response.PlacementResponse;
import com.vedantu.counselling.data.service.CounsellingDataService;
import com.vedantu.counselling.data.service.PlacementDataService;
import com.vedantu.counselling.data.view.CounsellingDataMetadata;
import com.vedantu.counselling.data.view.PlacementMetadata;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.view.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/counsellingapp")
@CrossOrigin
public class PlacementViewController {

    private final PlacementDataService placementDataService;

    @Autowired
    public PlacementViewController(PlacementDataService placementDataService) {
        this.placementDataService = placementDataService;
    }

    @GetMapping(value = "/placements-metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<PlacementMetadata> getPlacementViewMetadata() {
        return new Response<>(ResponseStatus.SUCCESS, placementDataService.getPlacementMetadata());
    }

    @PostMapping(value = "/placements", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<ListResponse<PlacementResponse>> getPlacements(@RequestBody PlacementRequest placementRequest) {
        return new Response<>(ResponseStatus.SUCCESS, placementDataService.getPlacementData(placementRequest));
    }

}
