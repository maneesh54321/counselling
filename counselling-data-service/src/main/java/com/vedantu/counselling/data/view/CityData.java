package com.vedantu.counselling.data.view;

import java.util.List;

import com.vedantu.counselling.data.model.City;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CityData {
    private City defaultCity;
    private List<City> allCity;
}
