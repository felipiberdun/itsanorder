package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.domain.Order;

import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface OrderService {

    Order findById(final Long id);

    Order create(final Order order);

    Customer findCustomerOrder(final Long orderId);

}
