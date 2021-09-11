package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.PlacementRecord;
import com.vedantu.counselling.data.repository.PlacementRepository;
import com.vedantu.counselling.data.response.ListResponse;
import com.vedantu.counselling.data.response.PlacementResponse;
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

    @Autowired
    PlacementRepository placementRepository;

    @PostMapping(value = "/placements", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<ListResponse<PlacementResponse>> getPlacements(@RequestBody PlacementFilter placementFilter) {
        List<PlacementRecord> allRecords = placementRepository.getRecords();
        List<PlacementRecord> filteredRecords = filteredRecords(allRecords, placementFilter);

        List<PlacementRecord> sortedRecords = sortRecords(filteredRecords, placementFilter.getSortBy());

        return new Response<>(ResponseStatus.SUCCESS, new ListResponse<>(getResponsePlacements(filteredRecords), sortedRecords.size()));
    }

    private List<PlacementRecord> sortRecords(List<PlacementRecord> filteredRecords, PlacementFilter.PlacementSortBy sortBy) {
        filteredRecords.sort(sortBy.getComparator());
        return filteredRecords;
    }

    private List<PlacementRecord> filteredRecords(List<PlacementRecord> placementRecords, PlacementFilter placementFilter){
        List<PlacementRecord> filteredRecords = new ArrayList<>(placementRecords);
        if(placementFilter.getColleges() != null && ! placementFilter.getColleges().isEmpty()){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getColleges().contains(r.getCollegeId()))
                    .collect(Collectors.toList());
        }
        if(placementFilter.getCollegeTypes() != null && ! placementFilter.getCollegeTypes().isEmpty()){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getCollegeTypes().contains(r.getCollegeTypeId()))
                    .collect(Collectors.toList());
        }
        if(placementFilter.getYear() != null && ! placementFilter.getYear().isEmpty()){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getYear().contains(r.getYear()))
                    .collect(Collectors.toList());
        }
        if(placementFilter.getUg_pg() != null && ! placementFilter.getUg_pg().isEmpty()){
            filteredRecords = filteredRecords.stream().filter(r -> placementFilter.getUg_pg().equals(r.getUgOrPg()))
                    .collect(Collectors.toList());
        }
        return filteredRecords;
    }


    private List<PlacementResponse> getResponsePlacements(List<PlacementRecord> placementList) {
        return placementList.stream().map(placement -> PlacementResponse
                        .builder()
                        .totalStudents(placement.getTotalStudents() == null ? 0:placement.getTotalStudents())
                        .studentPlacedPercent(placement.getStudentPlacedPercentage()
                                == null ? BigDecimal.ZERO:placement.getStudentPlacedPercentage())
                        .studentHigherStudyPercent(placement.getStudentHigherStudyPercentage()
                                == null ? BigDecimal.ZERO:placement.getStudentHigherStudyPercentage())
                        .averagePackage(placement.getAveragePackage()== null ? 0:placement.getAveragePackage())
                        .maxPackage(placement.getMaxPackage() == null ? 0:placement.getMaxPackage())
                        .minPackage(placement.getMinPackage() == null ? 0:placement.getMinPackage())
                        .year(placement.getYear())
                        .college(placement.getCollegeName())
                        .collegeType(placement.getCollegeType())
                        .ugOrPg(placement.getUgOrPg())
                        .build())
                .collect(Collectors.toList());
    }
}
