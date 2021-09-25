package com.vedantu.counselling.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounsellingDataResponse {
    private int totalRecord;
    private int cityId;
    private List<CounsellingData> counsellingData;
}
