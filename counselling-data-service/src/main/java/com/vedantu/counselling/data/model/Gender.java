package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_id_generator")
    @SequenceGenerator(name="gender_id_generator", sequenceName = "gender_seq", allocationSize = 5)
    private int genderId;

    private String genderName;
}
