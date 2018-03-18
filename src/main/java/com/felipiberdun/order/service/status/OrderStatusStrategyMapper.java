package com.felipiberdun.order.service.status;

import com.felipiberdun.order.domain.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class OrderStatusStrategyMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStatusStrategyMapper.class);
    private static final Map<OrderStatus, OrderStatusStrategy> VALUES_MAP = new HashMap<>();

    static {
        VALUES_MAP.put(OrderStatus.CREATED, new CreatedStatusStrategy());
        VALUES_MAP.put(OrderStatus.ACCEPTED, new AcceptedStatusStrategy());
        VALUES_MAP.put(OrderStatus.PREPARING, new PreparingStatusStrategy());
        VALUES_MAP.put(OrderStatus.ON_THE_WAY, new OnTheWayStatusStrategy());
        VALUES_MAP.put(OrderStatus.CANCELLED, new CancelledStatusStrategy());
        VALUES_MAP.put(OrderStatus.DELIVERIED, new DeliveriedStatusStrategy());
    }

    public static OrderStatusStrategy findStrategy(final OrderStatus orderStatus) {
        return Optional.ofNullable(VALUES_MAP.get(orderStatus))
                .orElseGet(() -> {
                    LOGGER.error("No Strategy was found to control status transition from {} - {}",
                            orderStatus.getId(), orderStatus.getDescription());
                    return new NotFoundStatusStrategy();
                });
    }

}
