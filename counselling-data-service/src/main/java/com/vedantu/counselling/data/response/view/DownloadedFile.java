package com.vedantu.counselling.data.response.view;

import lombok.Data;

@Data
public class DownloadedFile {
    private final String name;
    private final byte[] bytes;
}
