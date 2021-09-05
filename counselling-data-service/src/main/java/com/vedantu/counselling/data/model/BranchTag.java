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
public class BranchTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_tag_id_generator")
    @SequenceGenerator(name="branch_tag_id_generator", sequenceName = "branch_tag_seq", allocationSize = 1)
    private Integer branchTagId;

    @Column(nullable = false, unique = true)
    private String branchTagName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BranchTag)) return false;
        BranchTag branchTag = (BranchTag) o;
        return getBranchTagName().equals(branchTag.getBranchTagName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBranchTagName());
    }
}
