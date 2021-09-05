package com.vedantu.counselling.data.model;


import javax.persistence.*;
import java.util.Date;
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
    private Date date;
}
