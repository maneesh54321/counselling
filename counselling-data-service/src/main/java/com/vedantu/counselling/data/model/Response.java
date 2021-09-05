package com.vedantu.counselling.data.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Response<T> {
    private String status;
    private T data;
}
