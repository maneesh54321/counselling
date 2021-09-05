package com.vedantu.counselling.data.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_generator")
    @SequenceGenerator(name="branch_id_generator", sequenceName = "branch_id_seq", allocationSize = 5)
    private int branchId;

    private String branchName;

    private int duration;

    @OneToOne
    @JoinColumn(name = "branch_tag_id")
    private BranchTag branchTag;
}
