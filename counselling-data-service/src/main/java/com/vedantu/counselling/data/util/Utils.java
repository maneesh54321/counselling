package com.vedantu.counselling.data.util;

import org.springframework.boot.web.server.MimeMappings;
import org.springframework.util.StringUtils;

import java.util.Map;

public class Utils {
    private static final Map<String, String> apacheOpenOfficeFileExtMimeTypeMap;
    static {
        apacheOpenOfficeFileExtMimeTypeMap = Map.of(
                "odt", "application/vnd.oasis.opendocument.text",
                "ods", "application/vnd.oasis.opendocument.spreadsheet",
                "odp", "application/vnd.oasis.opendocument.presentation"
        );

    }
    public static String getContentTypeFromFileName(String fileName){
        String type = StringUtils.getFilenameExtension(fileName);
        String contentType = MimeMappings.DEFAULT.get(type);
        contentType = contentType == null ? apacheOpenOfficeFileExtMimeTypeMap.get(type) : contentType;
        contentType = contentType == null ? "application/octet-stream" : contentType;
        return contentType;
    }
}
