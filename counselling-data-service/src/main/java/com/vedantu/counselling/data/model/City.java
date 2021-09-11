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
    @SequenceGenerator(name="city_id_generator", sequenceName = "city_id_seq", allocationSize = 1)
    private Integer cityId;

    private String displayName;
    private String fullName;

    @Column(nullable = false)
    private boolean isDefault;

    public City(int cityId, String cityDisplayName, String cityFullName) {
        this.cityId = cityId;
        this.displayName = cityDisplayName;
        this.fullName = cityFullName;
    }
}
