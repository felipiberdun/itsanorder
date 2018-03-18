package com.felipiberdun.order.controller;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/customers/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Customer findById(@PathVariable("id") final Long id) {
        return customerService.findById(id);
    }

    @PostMapping(value = "/api/v1/customers", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> create(@RequestBody final Customer customer) {
        final Customer createdCustomer = customerService.create(customer);

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCustomer.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
