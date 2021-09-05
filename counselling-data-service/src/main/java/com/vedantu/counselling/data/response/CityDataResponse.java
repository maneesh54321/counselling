package com.vedantu.counselling.data.response;

import java.util.List;

import com.vedantu.counselling.data.model.City;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CityDataResponse {
    private City defaultCity;
    private List<City> allCity;
}
