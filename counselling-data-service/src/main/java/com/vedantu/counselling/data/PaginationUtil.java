package com.vedantu.counselling.data;

import java.util.List;

public class PaginationUtil {

    public static <T> List<T> getPaginatedList(List<T> finalCounsellingData, int size, int pageSize, int pageNumber) {
        if(size <= pageSize) {
            return finalCounsellingData;
        }
        if(( pageNumber +1) * pageSize < size) {
            return finalCounsellingData.subList((pageNumber -1) * pageSize, pageNumber * pageSize);
        } else {
            return finalCounsellingData.subList((pageNumber -1) * pageSize, size);
        }
    }
}
