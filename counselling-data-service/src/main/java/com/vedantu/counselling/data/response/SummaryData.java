package com.vedantu.counselling.data.response;

import com.vedantu.counselling.data.response.view.Download;
import com.vedantu.counselling.data.response.view.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SummaryData {
    private String description;
    private String disclaimer;
    private List<Video> videos;
    private List<Download> downloads;

    public SummaryData(String description, String disclaimer) {
        this.description = description;
        this.disclaimer = disclaimer;
    }

    public void addVideo(Video newVideo){
        if(videos == null){
            videos = new ArrayList<>();
        }
        videos.add(newVideo);
    }

    public void addDownload(Download newDownload){
        if(downloads == null){
            downloads = new ArrayList<>();
        }
        downloads.add(newDownload);
    }
}