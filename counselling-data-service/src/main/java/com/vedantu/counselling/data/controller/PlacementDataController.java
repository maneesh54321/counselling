package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.request.PlacementRequest;
import com.vedantu.counselling.data.response.ListResponse;
import com.vedantu.counselling.data.response.PlacementData;
import com.vedantu.counselling.data.service.PlacementDataService;
import com.vedantu.counselling.data.response.metadata.PlacementMetadata;
import com.vedantu.counselling.data.response.Response;
import com.vedantu.counselling.data.response.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/colleges/placements")
@CrossOrigin
@Slf4j
public class PlacementDataController {

    private final PlacementDataService placementDataService;

    @Autowired
    public PlacementDataController(PlacementDataService placementDataService) {
        this.placementDataService = placementDataService;
    }

    @GetMapping(value = "/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<PlacementMetadata> getPlacementViewMetadata() {
        return new Response<>(ResponseStatus.SUCCESS, placementDataService.getPlacementMetadata());
    }

    @PostMapping(value = "/query/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<ListResponse<PlacementData>> getPlacements(@RequestBody PlacementRequest placementRequest) {
        return new Response<>(ResponseStatus.SUCCESS, placementDataService.getPlacementData(placementRequest));
    }

}
