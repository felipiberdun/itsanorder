package com.felipiberdun.order.dto.external;

import java.io.Serializable;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class StoreDto implements Serializable {

    private Long id;
    private String name;
    private String address;
    private Long cousineId;

    public StoreDto() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCousineId() {
        return cousineId;
    }

    public void setCousineId(Long cousineId) {
        this.cousineId = cousineId;
    }

}
