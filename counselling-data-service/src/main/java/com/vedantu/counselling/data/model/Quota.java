package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quota_id_generator")
    @SequenceGenerator(name="quota_id_generator", sequenceName = "quota_seq", allocationSize = 5)
    private int quotaId;

    private String quotaName;
}
