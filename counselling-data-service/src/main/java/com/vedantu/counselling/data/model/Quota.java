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
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quota_id_generator")
    @SequenceGenerator(name="quota_id_generator", sequenceName = "quota_seq", allocationSize = 1)
    private int quotaId;

    @Column(unique = true, nullable = false)
    private String quotaName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quota)) return false;
        Quota quota = (Quota) o;
        return getQuotaName().equals(quota.getQuotaName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuotaName());
    }
}
