package com.vedantu.counselling.data.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CounsellingDataResponse {
    private int totalRecord;
    private List<CounsellingData> counsellingData;
}
