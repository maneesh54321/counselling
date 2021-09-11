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
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quota_id_generator")
    @SequenceGenerator(name="quota_id_generator", sequenceName = "quota_seq", allocationSize = 1)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quota)) return false;
        Quota quota = (Quota) o;
        return getName().equals(quota.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
