package com.vedantu.counselling.data.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_generator")
    @SequenceGenerator(name="city_id_generator", sequenceName = "city_id_seq", allocationSize = 5)
    private int cityId;

    private String cityDisplayName;
    private String cityFullName;
}
