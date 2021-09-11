package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.College;
import com.vedantu.counselling.data.model.CollegeType;
import com.vedantu.counselling.data.model.Placement;
import com.vedantu.counselling.data.repository.PlacementRepository;
import com.vedantu.counselling.data.response.PlacementResponse;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.view.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/counsellingapp")
@CrossOrigin
public class PlacementViewController {

    @Autowired
    EntityManager em;

    @Autowired
    PlacementRepository placementRepository;

    @PostMapping(value = "/placements", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<PlacementResponse>> getPlacements(@RequestBody PlacementFilter placementFilter) {

        if (placementFilter == null)
            return new Response<>(ResponseStatus.SUCCESS, getResponsePlacements(placementRepository.findAll()));

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Placement> cQuery = cb.createQuery(Placement.class);
        Root<Placement> root = cQuery.from(Placement.class);
        Predicate predicate = convertFilterToPredicate(cb, root, placementFilter);
        if (predicate != null){
            CriteriaQuery<Placement> finalQuery = cQuery.select(root).where(predicate);
            TypedQuery<Placement> query = em.createQuery(finalQuery);
            List<Placement> placementList = query.getResultList();
            return new Response<>(ResponseStatus.SUCCESS, getResponsePlacements(placementList));
        }
        return new Response<>(ResponseStatus.SUCCESS, getResponsePlacements(placementRepository.findAll()));
    }

    private List<PlacementResponse> getResponsePlacements(List<Placement> placementList) {
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
                        .college(placement.getCollege().getName())
                        .collegeType(placement.getCollege().getType().toString())
                        .ugOrPg(placement.getUgOrPg())
                        .build())
                .collect(Collectors.toList());
    }

    private Predicate convertFilterToPredicate(CriteriaBuilder cb, Root<Placement> root,
                                               PlacementFilter placementFilter) {
        Predicate predicate = null;
        if(placementFilter.getColleges() != null && ! placementFilter.getColleges().isEmpty()) {
            Join<Placement, College> collegeJoin = root.join( "college" );
            predicate = collegeJoin.get( "id" ).in(placementFilter.getColleges());
        }
        if(placementFilter.getCollegeTypes() != null && ! placementFilter.getCollegeTypes().isEmpty()) {
            Join<Placement, College> childJoin = root.join( "college" );
            Join<College, CollegeType> collegeTypeJoin= childJoin.join("type");
            Predicate collegeTypePredicate = collegeTypeJoin.get( "id" ).in(placementFilter.getCollegeTypes());
            predicate = predicate!= null? cb.and(predicate, collegeTypePredicate): collegeTypePredicate;
        }
        if(placementFilter.getYear() != null && ! placementFilter.getYear().isEmpty()){
            Predicate yearPredicate = root.get("year").in(placementFilter.getYear());
            predicate = predicate!= null? cb.and(predicate, yearPredicate): yearPredicate;
        }
        if(placementFilter.getUg_pg() != null && ! placementFilter.getUg_pg().isEmpty()){
            Predicate ugPgPredicate = cb.equal(root.get("ugOrPg"), placementFilter.getUg_pg());
            predicate = predicate!= null? cb.and(predicate, ugPgPredicate): ugPgPredicate;
        }
        return predicate;
    }

}
