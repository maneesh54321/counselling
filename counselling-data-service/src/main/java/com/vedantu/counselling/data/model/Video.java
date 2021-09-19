package com.vedantu.counselling.data.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video_id_generator")
    @SequenceGenerator(initialValue = 100, name="video_id_generator", sequenceName = "video_id_seq", allocationSize = 1)
    private int id;

    private String name;
    private String uri;
    private boolean active;

}
