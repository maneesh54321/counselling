package com.vedantu.counselling.data.response.view;

import java.util.List;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CityData {
    private CityView defaultCity;
    private List<CityView> allCity;
}
