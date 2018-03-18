package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.domain.Order;
import com.felipiberdun.order.repository.OrderRepository;
import com.felipiberdun.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> findById(final Long id) {
        return Optional.ofNullable(orderRepository.findOne(id));
    }

    @Transactional
    @Override
    public Order create(final Order order) {
        return orderRepository.saveAndFlush(order);
    }

}
