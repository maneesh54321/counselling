package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class DistanceMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "distance_mapping_id_generator")
    @SequenceGenerator(name="distance_mapping_id_generator", sequenceName = "distance_mapping_id_seq", allocationSize = 1)
    private int distanceMappingId;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne
    @JoinColumn(name = "college_id")
    private College college;

    private Integer distance;
}
