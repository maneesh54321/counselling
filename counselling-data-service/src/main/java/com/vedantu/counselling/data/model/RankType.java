package com.vedantu.counselling.data.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class RankType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rank_type_id_generator")
    @SequenceGenerator(name="rank_type_id_generator", sequenceName = "rank_type_seq", allocationSize = 5)
    private int rankTypeId;

    private String rankTypeName;
}
