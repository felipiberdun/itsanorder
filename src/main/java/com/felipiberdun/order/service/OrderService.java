package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Order;
import com.felipiberdun.order.dto.OrderStatusChangeRequest;
import com.felipiberdun.order.dto.output.CustomerDto;
import com.felipiberdun.order.dto.output.OrderDto;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface OrderService {

    OrderDto findById(final Long id);

    OrderDto create(final Order order);

    CustomerDto findCustomerOrder(final Long orderId);

    OrderDto updateStatus(final Long id, final OrderStatusChangeRequest statusChangeRequest);

}
