package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class CollegeType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_type_id_generator")
    @SequenceGenerator(name="college_type_id_generator", sequenceName = "college_type_id_seq", allocationSize = 5)
    private int collegeTypeId;

    private String collegeTypeName;
}
