package com.felipiberdun.order.dto.external;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class LoginCredentials {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public LoginCredentials() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
