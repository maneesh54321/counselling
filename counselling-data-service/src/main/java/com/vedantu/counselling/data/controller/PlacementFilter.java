package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.BranchTag;
import lombok.Data;

import java.util.List;

@Data
public class PlacementFilter {
    private List<Integer> colleges;
    private List<Integer> tags;
    private List<Integer> year;
    private String ug_pg;
}
