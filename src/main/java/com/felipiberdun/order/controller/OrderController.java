package com.felipiberdun.order.controller;

import com.felipiberdun.order.domain.Order;
import com.felipiberdun.order.domain.OrderStatus;
import com.felipiberdun.order.dto.OrderStatusChangeRequest;
import com.felipiberdun.order.dto.external.CustomerDto;
import com.felipiberdun.order.dto.external.OrderDto;
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
@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/orders/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderDto findById(@PathVariable("id") final Long id) {
        return orderService.findById(id);
    }

    @PostMapping(value = "/api/v1/orders", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> create(@RequestBody final Order order) {
        final OrderDto createdOrder = orderService.create(order);

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOrder.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/orders/{id}/customer")
    public CustomerDto findCustomer(@PathVariable("id") final Long orderId) {
        return orderService.findCustomerOrder(orderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/orders/{id}/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderStatus findOrderStatus(@PathVariable("id") final Long id) {
        return orderService.findById(id).getStatus();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/api/v1/orders/{id}/status")
    public void updateStatus(@PathVariable("id") final Long id,
                             @RequestBody final OrderStatusChangeRequest statusChangeRequest) {
        orderService.updateStatus(id, statusChangeRequest);
    }

}
