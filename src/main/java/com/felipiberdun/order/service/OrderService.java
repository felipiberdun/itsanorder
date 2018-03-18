package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.domain.Order;
import com.felipiberdun.order.domain.OrderStatus;
import com.felipiberdun.order.dto.OrderStatusChangeRequest;

import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface OrderService {

    Order findById(final Long id);

    Order create(final Order order);

    Customer findCustomerOrder(final Long orderId);

    Order updateStatus(final Long id, final OrderStatusChangeRequest statusChangeRequest);

}
