package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.Placement;
import com.vedantu.counselling.data.repository.PlacementRepository;
import com.vedantu.counselling.data.response.PlacementResponse;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.view.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    @GetMapping(value = "/placements", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<PlacementResponse>> getPlacements(PlacementFilter placementFilter){

        if(placementFilter == null)
            return new Response<>(ResponseStatus.SUCCESS, getResponsePlacements(placementRepository.findAll()));

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Placement> cQuery = cb.createQuery(Placement.class);
        Root<Placement> root = cQuery.from(Placement.class);
        Predicate predicate = convertFilterToParameter(cb, root, placementFilter);
        CriteriaQuery<Placement> finalQuery = cQuery.select(root).where(predicate);
        TypedQuery<Placement> query = em.createQuery(finalQuery);
        List<Placement> placementList = query.getResultList();
        return new Response<>(ResponseStatus.SUCCESS, getResponsePlacements(placementList));
    }

    private List<PlacementResponse> getResponsePlacements(List<Placement> placementList) {
        return placementList.stream().map(placement -> PlacementResponse
                        .builder()
                        .noOfStudent(placement.getTotalNoOfStudent())
                        .noOfPlacedStudent(placement.getNoOfPlacedStudent())
                        .noOfHigherStudyCount(placement.getNoOfHigherStudy())
                        .averagePackage(placement.getAveragePackage())
                        .maxPackage(placement.getMaxPackage())
                        .minPackage(placement.getMinPackage())
                        .year(placement.getYear())
                        .collegeName(placement.getCollege().getCollegeName())
                        .tag(placement.getCollege().getCollegeType().toString())
                        .ugOrPg(placement.getUgOrPg())
                        .build())
                .collect(Collectors.toList());
    }

    private Predicate convertFilterToParameter(CriteriaBuilder cb, Root<Placement> root,
                                          PlacementFilter placementFilter) {
        Predicate predicate = null;
       /* if(placementFilter.getColleges() != null) {
            Join<Placement, CollegeBranch> childJoin = root.join( "college" );
            predicate = cb.in( childJoin.get( "id" ), placementFilter.getColleges() );
        }*/
        if(placementFilter.getYear() != null){
            Predicate yearPredicate = root.get("year").in(placementFilter.getYear());
            predicate = predicate!= null? cb.and(predicate, yearPredicate): yearPredicate;
        }
        return predicate;
    }

}
