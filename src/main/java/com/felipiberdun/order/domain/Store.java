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
@Table(name = "STORE")
public class Store {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 120)
    @Column(name = "NAME")
    private String name;

    @NotBlank
    @Size(max = 500)
    @Column(name = "FULL_ADDRESS")
    private String fullAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUSINE_ID", referencedColumnName = "ID")
    private Cousine cousine;

    public Store() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Cousine getCousine() {
        return cousine;
    }

    public void setCousine(Cousine cousine) {
        this.cousine = cousine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
