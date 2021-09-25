package com.vedantu.counselling.data.response.view;

import lombok.Getter;

@Getter
public class DisclaimerView {

    private final String type;
    private final String content;

    public DisclaimerView(String type, String content) {
        this.type = type;
        this.content = content;
    }
}
