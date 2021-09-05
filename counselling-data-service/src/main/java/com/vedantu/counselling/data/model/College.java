package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_id_generator")
    @SequenceGenerator(name="college_id_generator", sequenceName = "college_id_seq", allocationSize = 5)
    private int collegeId;

    private String collegeName;

    @OneToOne
    @JoinColumn(name = "college_type_id")
    private CollegeType collegeType;
}
