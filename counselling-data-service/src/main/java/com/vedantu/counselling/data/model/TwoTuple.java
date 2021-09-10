package com.vedantu.counselling.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TwoTuple<U,V>{
    private U first;
    private V second;
}
