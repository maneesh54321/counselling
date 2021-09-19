package com.vedantu.counselling.data.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class SummaryData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summary_id_generator")
    @SequenceGenerator(initialValue = 100, name="summary_id_generator", sequenceName = "summary_id_seq", allocationSize = 1)
    private int id;

    private String description;
    private String disclaimer;
}
