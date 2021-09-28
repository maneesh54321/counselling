package com.vedantu.counselling.data.service.mapper;

import com.vedantu.counselling.data.model.CounsellingDataFile;
import com.vedantu.counselling.data.model.DisclaimerData;
import com.vedantu.counselling.data.response.view.DisclaimerView;
import com.vedantu.counselling.data.response.view.Download;

import java.util.List;
import java.util.stream.Collectors;

public class SummaryServiceDataMapper {
    public static List<Download> mapDownloads(List<CounsellingDataFile> counsellingDataFiles) {
        return counsellingDataFiles.parallelStream()
                .map(file -> new Download(file.getId(), file.getName(), file.getDescription()))
                .collect(Collectors.toList());
    }

    public static List<DisclaimerView> mapDisclaimer(List<DisclaimerData> disclaimerData) {
        return disclaimerData.parallelStream()
                .map(disclaimer -> new DisclaimerView(disclaimer.getType(), disclaimer.getContent()))
                .collect(Collectors.toList());
    }
}
