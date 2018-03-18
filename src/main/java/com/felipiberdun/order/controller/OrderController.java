package com.felipiberdun.order.controller;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.domain.Order;
import com.felipiberdun.order.service.OrderService;
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
@RestController(value = "/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Order findById(@PathVariable("id") final Long id) {
        return orderService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> create(@RequestBody final Order order) {
        final Order createdOrder = orderService.create(order);

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOrder.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/customer")
    public Customer findCustomer(@PathVariable("id") final Long orderId) {
        return orderService.findCustomerOrder(orderId);
    }

}
