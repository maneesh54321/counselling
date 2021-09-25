package com.vedantu.counselling.data.response;

import com.vedantu.counselling.data.response.view.DisclaimerView;
import com.vedantu.counselling.data.response.view.Download;
import com.vedantu.counselling.data.response.view.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SummaryData {
    private String description;
    private List<DisclaimerView> disclaimer = new ArrayList<>();
    private List<Video> videos = new ArrayList<>();
    private List<Download> downloads = new ArrayList<>();

    public SummaryData(String description) {
        this.description = description;
    }

    public void addVideo(Video newVideo){
        videos.add(newVideo);
    }

    public void addDownload(Download newDownload){
        downloads.add(newDownload);
    }
}
