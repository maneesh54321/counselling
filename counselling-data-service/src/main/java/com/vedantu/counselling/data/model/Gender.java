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
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_id_generator")
    @SequenceGenerator(name="gender_id_generator", sequenceName = "gender_seq", allocationSize = 1)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gender)) return false;
        Gender gender = (Gender) o;
        return getName().equals(gender.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
