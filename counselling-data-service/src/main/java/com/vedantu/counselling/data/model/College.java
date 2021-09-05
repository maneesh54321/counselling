package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private Integer collegeId;

    @Column(unique = true)
    private String collegeName;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "college_type_id")
    private CollegeType collegeType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof College)) return false;
        College college = (College) o;
        return getCollegeName().equals(college.getCollegeName()) && getCollegeType().equals(college.getCollegeType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCollegeName(), getCollegeType());
    }
}
