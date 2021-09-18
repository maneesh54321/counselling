package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.response.Response;
import com.vedantu.counselling.data.response.ResponseStatus;
import com.vedantu.counselling.data.response.metadata.CollegeBranchMetaData;
import com.vedantu.counselling.data.service.AccessTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/access-tracker")
public class AccessTrackerController {

    private AccessTrackerService accessTrackerService;

    @Autowired
    public AccessTrackerController(AccessTrackerService accessTrackerService) {
        this.accessTrackerService = accessTrackerService;
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Integer> getCollegeBranchMetaData() {
        return new Response<>(ResponseStatus.SUCCESS, accessTrackerService.getAccessCount());
    }

}
