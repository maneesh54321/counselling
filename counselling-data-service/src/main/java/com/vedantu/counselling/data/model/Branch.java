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
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_generator")
    @SequenceGenerator(name="branch_id_generator", sequenceName = "branch_id_seq", allocationSize = 1)
    private Integer branchId;

    @Column(nullable = false)
    private String branchName;

    private Integer duration;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "branch_tag_id")
    private BranchTag branchTag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;
        Branch branch = (Branch) o;
        return getDuration() == branch.getDuration() && getBranchName().equals(branch.getBranchName()) && getBranchTag().equals(branch.getBranchTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBranchName(), getDuration(), getBranchTag());
    }
}
