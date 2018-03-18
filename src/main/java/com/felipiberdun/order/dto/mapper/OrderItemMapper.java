package com.felipiberdun.order.dto.mapper;

import com.felipiberdun.order.domain.OrderItem;
import com.felipiberdun.order.dto.external.OrderItemDto;
import org.mapstruct.Mapper;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemDto toDto(final OrderItem orderItem);

}
