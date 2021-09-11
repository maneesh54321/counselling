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
public class CollegeType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_type_id_generator")
    @SequenceGenerator(name="college_type_id_generator", sequenceName = "college_type_id_seq", allocationSize = 1)
    private int id;

    @Column(unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollegeType)) return false;
        CollegeType that = (CollegeType) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
