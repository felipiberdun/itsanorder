package com.felipiberdun.order.controller;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.domain.Token;
import com.felipiberdun.order.dto.output.LoginCredentials;
import com.felipiberdun.order.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/api/v1/customers/auth", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Token authenticate(@RequestBody final LoginCredentials loginCredentials) {
        final Customer customer = authenticationService.authenticate(loginCredentials.getEmail(), loginCredentials.getPassword());

        return Token.of(customer.getId()).buildAuthenticationToken();
    }

}
