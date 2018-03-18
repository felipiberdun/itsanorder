package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Order;

import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface OrderService {

    Optional<Order> findById(final Long id);

    Order create(final Order order);

}
