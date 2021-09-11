package com.vedantu.counselling.data.model;

import javax.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class RankType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rank_type_id_generator")
    @SequenceGenerator(name="rank_type_id_generator", sequenceName = "rank_type_seq", allocationSize = 1)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RankType)) return false;
        RankType rankType = (RankType) o;
        return getName().equals(rankType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
