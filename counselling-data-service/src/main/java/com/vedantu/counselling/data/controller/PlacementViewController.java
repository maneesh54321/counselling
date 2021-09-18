package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.request.PlacementRequest;
import com.vedantu.counselling.data.response.ListResponse;
import com.vedantu.counselling.data.response.PlacementData;
import com.vedantu.counselling.data.service.PlacementDataService;
import com.vedantu.counselling.data.util.PathConstants;
import com.vedantu.counselling.data.response.metadata.PlacementMetadata;
import com.vedantu.counselling.data.response.Response;
import com.vedantu.counselling.data.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathConstants.COUNSELLINGAPP)
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
    public Response<ListResponse<PlacementData>> getPlacements(@RequestBody PlacementRequest placementRequest) {
        return new Response<>(ResponseStatus.SUCCESS, placementDataService.getPlacementData(placementRequest));
    }

}
