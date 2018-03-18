package com.felipiberdun.order.service.status;

import com.felipiberdun.order.domain.OrderStatus;
import com.felipiberdun.order.dto.Source;

import java.util.stream.Stream;

import static com.felipiberdun.order.domain.OrderStatus.CANCELLED;
import static com.felipiberdun.order.domain.OrderStatus.PREPARING;
import static com.felipiberdun.order.dto.Source.CUSTOMER;
import static com.felipiberdun.order.dto.Source.STORE;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class AcceptedStatusStrategy implements OrderStatusStrategy {

    private static final Stream<OrderStatus> STORE_ALLOWED_STATUS = Stream.of(PREPARING, CANCELLED);
    private static final Stream<OrderStatus> CUSTOMER_ALLOWED_STATUS = Stream.of(CANCELLED);

    @Override
    public boolean validateTransition(final Source source, final OrderStatus orderStatus) {
        return ((STORE.equals(source) && STORE_ALLOWED_STATUS.anyMatch(orderStatus::equals))
                || (CUSTOMER.equals(source) && CUSTOMER_ALLOWED_STATUS.anyMatch(orderStatus::equals)));
    }

}
