package com.vedantu.counselling.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ThreeTuple <U,V,W>{
    private U first;
    private V second;
    private W third;
}
