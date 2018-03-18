package com.felipiberdun.order.dto.mapper;

import com.felipiberdun.order.domain.Product;
import com.felipiberdun.order.dto.external.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "storeId", source = "store.id")
    ProductDto toDto(final Product product);

}
