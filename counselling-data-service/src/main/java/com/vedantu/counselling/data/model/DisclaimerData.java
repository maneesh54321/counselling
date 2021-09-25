package com.vedantu.counselling.data.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class DisclaimerData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disclaimer_id_generator")
    @SequenceGenerator(initialValue = 100, name="disclaimer_id_generator", sequenceName = "disclaimer_id_seq", allocationSize = 1)
    private int id;

    private String type;
    private String content;

}
