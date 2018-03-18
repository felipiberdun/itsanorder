package com.felipiberdun.order.dto.mapper;

import com.felipiberdun.order.domain.Order;
import com.felipiberdun.order.domain.OrderItem;
import com.felipiberdun.order.dto.output.OrderDto;
import com.felipiberdun.order.dto.output.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Mappings(value = {
            @Mapping(target = "customerId", source = "order.customer.id"),
            @Mapping(target = "storeId", source = "order.store.id"),
            @Mapping(target = "orderItems", expression = "java(convertItems(order.getOrderItems()))")
    })
    public abstract OrderDto toDto(final Order order);

    public List<OrderItemDto> convertItems(final List<OrderItem> orderItems) {
        return Optional.ofNullable(orderItems).orElse(new ArrayList<>())
                .stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

}
