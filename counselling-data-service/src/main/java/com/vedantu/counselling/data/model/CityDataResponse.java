package com.vedantu.counselling.data.model;

import java.util.List;
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
