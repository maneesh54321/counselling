package com.vedantu.counselling.data.response;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Response<T> {
    private ResponseStatus status;
    private T data;
}
