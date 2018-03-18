package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.domain.Token;
import com.felipiberdun.order.exception.EntityNotFoundException;
import com.felipiberdun.order.repository.CustomerRepository;
import com.felipiberdun.order.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(final CustomerRepository customerRepository,
                                     final PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer authenticate(final String email, final String password) {
        final Customer customer = customerRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);

        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new BadCredentialsException("Bad credentials!");
        }

        return customer;
    }

    @Override
    public Customer validate(final Token token) {
        return customerRepository.findById(token.getCustomerId()).orElseThrow(EntityNotFoundException::new);
    }

}
