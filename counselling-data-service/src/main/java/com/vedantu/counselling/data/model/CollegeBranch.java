package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class CollegeBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_branch_id_generator")
    @SequenceGenerator(name="college_branch_id_generator", sequenceName = "college_branch_id_seq", allocationSize = 5)
    private int collegeBranchId;

    @OneToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToOne
    @JoinColumn(name = "college_id")
    private College college;
}
