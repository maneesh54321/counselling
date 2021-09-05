package com.vedantu.counselling.data.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class AccessTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "access_id_generator")
    @SequenceGenerator(name="access_id_generator", sequenceName = "access_id_seq", allocationSize = 5)
    private int accessId;

    private String ip;

    @Column(nullable = false)
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccessTracker)) return false;
        AccessTracker that = (AccessTracker) o;
        return getIp().equals(that.getIp()) && getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIp(), getDate());
    }
}
