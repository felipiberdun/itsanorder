package com.felipiberdun.order.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Entity
@Table(name = "COUSINE")
public class Cousine {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;

    public Cousine() {
    }

    public Cousine(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cousine cousine = (Cousine) o;
        return Objects.equals(id, cousine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
