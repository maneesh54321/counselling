package com.vedantu.counselling.data.model;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rank_id_generator")
    @SequenceGenerator(name="rank_id_generator", sequenceName = "rank_id_seq", allocationSize = 5)
    private int rankId;

    @OneToOne
    @JoinColumn(name = "college_branch_id")
    private CollegeBranch collegeBranch;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "quota_id")
    private Quota quota;

    @OneToOne
    @JoinColumn(name = "rank_type_id")
    private RankType rankType;

    private int year;
    private int openRank;
    private int closingRank;
}
