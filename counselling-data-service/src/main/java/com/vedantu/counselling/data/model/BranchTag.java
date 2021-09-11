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
public class BranchTag implements Comparable<BranchTag>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_tag_id_generator")
    @SequenceGenerator(name="branch_tag_id_generator", sequenceName = "branch_tag_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BranchTag)) return false;
        BranchTag branchTag = (BranchTag) o;
        return getName().equals(branchTag.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public int compareTo(BranchTag o) {
        return this.name.compareTo(o.name);
    }
}
