package com.vedantu.counselling.data.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placement_id_generator")
    @SequenceGenerator(name="placement_id_generator", sequenceName = "placement_seq", allocationSize = 1)
    private int id;

    private int year;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "college_id")
    private College college;

    private Integer averagePackage;
    private Integer maxPackage;
    private Integer minPackage;
    private String ugOrPg;

    private Integer totalStudents;

    private BigDecimal studentPlacedPercentage;
    private BigDecimal studentHigherStudyPercentage;
}
