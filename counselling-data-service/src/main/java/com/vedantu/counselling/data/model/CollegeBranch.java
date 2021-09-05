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
public class CollegeBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_branch_id_generator")
    @SequenceGenerator(name="college_branch_id_generator", sequenceName = "college_branch_id_seq", allocationSize = 1)
    private int collegeBranchId;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "college_id")
    private College college;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollegeBranch)) return false;
        CollegeBranch that = (CollegeBranch) o;
        return getBranch().equals(that.getBranch()) && getCollege().equals(that.getCollege());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBranch(), getCollege());
    }
}
