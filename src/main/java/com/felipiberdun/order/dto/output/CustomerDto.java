package com.felipiberdun.order.dto.output;

import java.time.LocalDateTime;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class CustomerDto {

    private Long id;
    private String email;
    private String name;
    private String address;
    private LocalDateTime creation;

    public CustomerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }
}
