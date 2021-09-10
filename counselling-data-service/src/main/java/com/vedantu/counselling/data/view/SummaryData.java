package com.vedantu.counselling.data.view;

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
}
