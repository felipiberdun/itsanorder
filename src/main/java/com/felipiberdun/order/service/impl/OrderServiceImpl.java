package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.domain.Order;
import com.felipiberdun.order.domain.OrderItem;
import com.felipiberdun.order.domain.OrderStatus;
import com.felipiberdun.order.dto.OrderStatusChangeRequest;
import com.felipiberdun.order.dto.external.CustomerDto;
import com.felipiberdun.order.dto.external.OrderDto;
import com.felipiberdun.order.dto.mapper.CustomerMapper;
import com.felipiberdun.order.dto.mapper.OrderMapper;
import com.felipiberdun.order.exception.EntityNotFoundException;
import com.felipiberdun.order.exception.OrderStatusException;
import com.felipiberdun.order.repository.CustomerRepository;
import com.felipiberdun.order.repository.OrderRepository;
import com.felipiberdun.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.felipiberdun.order.service.status.OrderStatusStrategyMapper.findStrategy;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository,
                            final CustomerRepository customerRepository,
                            final OrderMapper orderMapper,
                            final CustomerMapper customerMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public OrderDto findById(final Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public OrderDto create(final Order order) {
        final Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(EntityNotFoundException::new);

        order.setDeliveryAddress(customer.getAddress());
        order.setContact(customer.getName());
        order.setDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        order.setLastUpdate(LocalDateTime.now());
        order.getOrderItems().forEach(item -> item.setOrder(order));
        order.setTotal(order.getOrderItems().stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        return orderMapper.toDto(orderRepository.saveAndFlush(order));
    }

    @Override
    public CustomerDto findCustomerOrder(final Long orderId) {
        final Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        return customerMapper.toDto(order.getCustomer());
    }

    @Override
    public OrderDto updateStatus(final Long id, final OrderStatusChangeRequest statusChangeRequest) {
        final Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        final OrderStatus currentStatus = order.getStatus();
        final OrderStatus newStatus = statusChangeRequest.getStatus();

        if (currentStatus.equals(newStatus)) {
            throw new OrderStatusException(currentStatus, newStatus);
        }

        if (!findStrategy(currentStatus).validateTransition(statusChangeRequest.getSource(), newStatus)) {
            throw new OrderStatusException(currentStatus, newStatus);
        }

        order.setStatus(newStatus);
        order.setLastUpdate(LocalDateTime.now());
        return orderMapper.toDto(orderRepository.saveAndFlush(order));
    }

}
