package com.vedantu.counselling.data.service.mapper;

import com.vedantu.counselling.data.model.CounsellingDataFile;
import com.vedantu.counselling.data.view.Download;

import java.util.List;
import java.util.stream.Collectors;

public class SummaryServiceDataMapper {
    public static List<Download> mapDownloads(List<CounsellingDataFile> counsellingDataFiles) {
        return counsellingDataFiles.parallelStream()
                .map(file -> new Download(file.getId(), file.getDescription()))
                .collect(Collectors.toList());
    }
}