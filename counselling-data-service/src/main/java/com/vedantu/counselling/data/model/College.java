package com.vedantu.counselling.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_id_generator")
    @SequenceGenerator(name="college_id_generator", sequenceName = "college_id_seq", allocationSize = 1)
    private int id;

    @Column(unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "college_type_id")
    private CollegeType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof College)) return false;
        College college = (College) o;
        return getName().equals(college.getName()) && getType().equals(college.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType());
    }
}
