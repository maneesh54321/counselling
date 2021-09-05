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
    @SequenceGenerator(name="rank_id_generator", sequenceName = "rank_id_seq", allocationSize = 1)
    private int rankId;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "college_branch_id")
    private CollegeBranch collegeBranch;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "quota_id")
    private Quota quota;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "rank_type_id")
    private RankType rankType;

    @Column(nullable = false)
    private int year;

    private int openRank;
    private int closingRank;
}
