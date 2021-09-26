package com.vedantu.counselling.data.util;

import com.vedantu.counselling.data.exception.InvalidInputException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PaginationUtil {

    public static <T> List<T> getPaginatedList(List<T> finalCounsellingData, int size, int pageSize,
                                               int pageNumber) {

        if(pageSize < 0)
            throw new InvalidInputException("Pagination with page size less than zero");

        if(pageNumber < 0)
            throw new InvalidInputException("Pagination with page number less than zero");

        if(finalCounsellingData.size() == 0) {
            return finalCounsellingData;
        }
        log.debug("Creating paginated data total records {}, page size {}, page Number  {}", size, pageSize, pageNumber);

        if((pageNumber -1) * pageSize > size)
        {
            throw new InvalidInputException("Data requested beyond range");
        }
        return finalCounsellingData.subList((pageNumber -1) * pageSize,
                Math.min(pageNumber*pageSize, size));
    }
}
