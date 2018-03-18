package com.felipiberdun.order.dto.mapper;

import com.felipiberdun.order.domain.OrderItem;
import com.felipiberdun.order.dto.external.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mappings(value = {
            @Mapping(target = "orderId", source = "orderItem.order.id"),
            @Mapping(target = "productId", source = "orderItem.product.id")
    })
    OrderItemDto toDto(final OrderItem orderItem);

}
