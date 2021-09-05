package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.City;
import com.vedantu.counselling.data.model.CityDataResponse;
import com.vedantu.counselling.data.model.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LandingPageController {

    @GetMapping(value = "/counsellingapp/get-all-city", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CityDataResponse> getAllCity() {
        return new Response<>("Success", getDummyCityReponse());
    }



    private CityDataResponse getDummyCityReponse(){
        CityDataResponse response = new CityDataResponse();
        response.setDefaultCity(new City(1, "Bengaluru", "Bengaluru, Karnataka"));
        List<City> allCity = new ArrayList<>();
        allCity.add(new City(1, "Bengaluru", "Bengaluru, Karnataka, India"));
        allCity.add(new City(2, "Mumbai", "Mumbai, Maharashtra, India"));
        allCity.add(new City(3, "New Delhi", "New Delhi, Delhi, India"));
        response.setAllCity(allCity);
        return response;
    }
}
