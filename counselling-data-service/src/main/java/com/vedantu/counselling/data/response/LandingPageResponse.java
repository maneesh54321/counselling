package com.vedantu.counselling.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class LandingPageResponse {

    private String description;
    private String disclaimer;
    private String videoLink;
}
