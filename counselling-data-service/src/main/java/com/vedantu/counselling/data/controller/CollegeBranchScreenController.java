package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.request.CollegeBranchRequest;
import com.vedantu.counselling.data.response.CollegeBranchResponse;
import com.vedantu.counselling.data.response.ListResponse;
import com.vedantu.counselling.data.service.CollegeBranchDataService;
import com.vedantu.counselling.data.view.CollegeBranchMetaData;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.view.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/counsellingapp")
public class CollegeBranchScreenController {

    private final CollegeBranchDataService collegeBranchDataService;

    @Autowired
    public CollegeBranchScreenController(CollegeBranchDataService collegeBranchDataService) {
        this.collegeBranchDataService = collegeBranchDataService;
    }

    @GetMapping(value = "/college-branch-metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CollegeBranchMetaData> getCollegeBranchMetaData() {
        return new Response<>(ResponseStatus.SUCCESS, collegeBranchDataService.getCollegeBranchMetaData());
    }

    @PostMapping(value = "/college-branch-get-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<ListResponse<CollegeBranchResponse>> getCollegeBranchDataFor(@RequestBody CollegeBranchRequest collegeBranchRequest) {
        return new Response<>(ResponseStatus.SUCCESS, collegeBranchDataService.getCollegeBranchDataFor(collegeBranchRequest));
    }
}
