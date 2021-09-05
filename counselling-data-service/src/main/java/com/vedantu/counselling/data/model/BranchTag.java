package com.vedantu.counselling.data.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class BranchTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_tag_id_generator")
    @SequenceGenerator(name="branch_tag_id_generator", sequenceName = "branch_tag_seq", allocationSize = 5)
    private int branchTagId;

    private String branchTagName;
}
