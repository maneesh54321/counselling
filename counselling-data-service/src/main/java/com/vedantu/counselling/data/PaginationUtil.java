package com.vedantu.counselling.data;

import com.vedantu.counselling.data.exception.InvalidInputException;

import java.util.List;

public class PaginationUtil {

    public static <T> List<T> getPaginatedList(List<T> finalCounsellingData, int size, int pageSize, int pageNumber) {
        if((pageNumber -1) * pageSize > size)
        {
            throw new InvalidInputException("Data requested beyond range");
        }
        return finalCounsellingData.subList((pageNumber -1) * pageSize,
                Math.min(pageNumber*pageSize, size));
    }
}
