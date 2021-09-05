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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_generator")
    @SequenceGenerator(name="category_id_generator", sequenceName = "category_seq", allocationSize = 1)
    private int categoryId;

    private String categoryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getCategoryName().equals(category.getCategoryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryName());
    }
}
