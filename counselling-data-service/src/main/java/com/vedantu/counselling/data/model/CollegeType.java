package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

import java.util.Objects;
import java.util.Set;

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
    private int collegeTypeId;

    @Column(unique = true)
    private String collegeTypeName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollegeType)) return false;
        CollegeType that = (CollegeType) o;
        return getCollegeTypeName().equals(that.getCollegeTypeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCollegeTypeName());
    }
}
