package com.felipiberdun.order.repository.jpa.converter;

import com.felipiberdun.order.domain.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final OrderStatus orderStatus) {
        return Optional.ofNullable(orderStatus).map(OrderStatus::getId).orElse(null);
    }

    @Override
    public OrderStatus convertToEntityAttribute(final Integer integer) {
        return Optional.ofNullable(integer).map(OrderStatus::fromId).orElse(null);
    }

}
