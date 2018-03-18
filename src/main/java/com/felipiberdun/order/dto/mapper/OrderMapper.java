package com.felipiberdun.order.dto.mapper;

import com.felipiberdun.order.domain.Order;
import com.felipiberdun.order.dto.external.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings(value = {
            @Mapping(target = "customerId", source = "order.customer.id"),
            @Mapping(target = "storeId", source = "order.store.id")
    })
    OrderDto toDto(final Order order);

}
