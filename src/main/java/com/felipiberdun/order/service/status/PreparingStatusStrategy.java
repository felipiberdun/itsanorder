package com.felipiberdun.order.service.status;

import com.felipiberdun.order.domain.OrderStatus;
import com.felipiberdun.order.dto.Source;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.felipiberdun.order.domain.OrderStatus.CANCELLED;
import static com.felipiberdun.order.domain.OrderStatus.ON_THE_WAY;
import static com.felipiberdun.order.dto.Source.CUSTOMER;
import static com.felipiberdun.order.dto.Source.STORE;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class PreparingStatusStrategy implements OrderStatusStrategy {

    private static final List<OrderStatus> STORE_ALLOWED_STATUS = Arrays.asList(ON_THE_WAY, CANCELLED);
    private static final List<OrderStatus> CUSTOMER_ALLOWED_STATUS = Collections.singletonList(CANCELLED);

    @Override
    public boolean validateTransition(final Source source, final OrderStatus orderStatus) {
        return ((STORE.equals(source) && STORE_ALLOWED_STATUS.stream().anyMatch(orderStatus::equals))
                || (CUSTOMER.equals(source) && CUSTOMER_ALLOWED_STATUS.stream().anyMatch(orderStatus::equals)));
    }

}
