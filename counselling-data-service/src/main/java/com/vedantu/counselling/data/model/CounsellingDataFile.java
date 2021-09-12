package com.vedantu.counselling.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CounsellingDataFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "counselling_data_file_id_generator")
    @SequenceGenerator(
            name="counselling_data_file_id_generator",
            sequenceName = "counselling_data_file_id_seq", allocationSize = 1
    )
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String description;

    @Basic
    @Column(nullable = false)
    private byte[] content;

    public CounsellingDataFile(String name, String description, byte[] content) {
        this.name = name;
        this.description = description;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CounsellingDataFile)) return false;
        CounsellingDataFile that = (CounsellingDataFile) o;
        return getName().equals(that.getName()) && getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }
}
