package com.vedantu.counselling.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListResponse<T> {
    private List<T> responseData;
    private long totalRecords;
}
