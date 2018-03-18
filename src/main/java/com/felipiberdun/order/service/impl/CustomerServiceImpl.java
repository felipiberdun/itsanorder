package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.dto.external.CustomerDto;
import com.felipiberdun.order.dto.mapper.CustomerMapper;
import com.felipiberdun.order.exception.EntityNotFoundException;
import com.felipiberdun.order.exception.ExistentUserException;
import com.felipiberdun.order.repository.CustomerRepository;
import com.felipiberdun.order.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository,
                               final CustomerMapper customerMapper,
                               final PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerDto findById(final Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public CustomerDto create(final Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new ExistentUserException();
        }

        customer.setCreation(LocalDateTime.now());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerMapper.toDto(customerRepository.saveAndFlush(customer));
    }

}
