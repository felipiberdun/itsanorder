package com.felipiberdun.order.dto.mapper;

import com.felipiberdun.order.domain.Store;
import com.felipiberdun.order.dto.output.StoreDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Mapper(componentModel = "spring")
public interface StoreMapper {

    @Mapping(target = "cousineId", source = "cousine.id")
    StoreDto toDto(final Store store);

}
