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
public class Branch implements Comparable<Branch> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_generator")
    @SequenceGenerator(name="branch_id_generator", sequenceName = "branch_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String name;

    private Integer duration;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "branch_tag_id")
    private BranchTag branchTag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;
        Branch branch = (Branch) o;
        return Objects.equals(getDuration(), branch.getDuration()) && getName().equals(branch.getName()) && getBranchTag().equals(branch.getBranchTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDuration(), getBranchTag());
    }

    @Override
    public int compareTo(Branch o) {
        if(this == o){
            return 0;
        }
        if(Objects.equals(this.branchTag, o.getBranchTag())){
            if(Objects.equals(this.name, o.getName())){
                return Integer.compare(this.duration, o.getDuration());
            }
            return this.name.compareTo(o.getName());
        }
        return this.branchTag.compareTo(o.getBranchTag());
    }
}
