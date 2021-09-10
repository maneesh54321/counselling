package com.vedantu.counselling.data.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity(name="placement")
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placement_id_generator")
    @SequenceGenerator(name="placement_id_generator", sequenceName = "placement_seq", allocationSize = 1)
    private int id;

    private int year;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "college_id")
    private College college;

    private int averagePackage;
    private int maxPackage;
    private int minPackage;
    private String ugOrPg;

    private int totalNoOfStudent;
    private int noOfPlacedStudent;
    private int noOfHigherStudy;
}
