package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class CollegePlacementData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_placement_data_id_generator")
    @SequenceGenerator(name="college_placement_data_id_generator", sequenceName = "clg_placement_data_id_seq", allocationSize = 1)
    private int collegePlacementDataId;

    @OneToOne
    @JoinColumn(name = "college_id")
    private College college;

    @Column(nullable = false)
    private Integer year;

    private int averageSalary;
    private int noOfStudent;
    private int noOfPlacedStudent;
    private int noOfHigherStudyCount;
}
