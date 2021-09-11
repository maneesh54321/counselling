package com.vedantu.counselling.data.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityView {
    private int id;
    private String displayName;
    private String fullName;
}
