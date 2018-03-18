package com.felipiberdun.order.dto.mapper;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.dto.external.CustomerDto;
import org.mapstruct.Mapper;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(final Customer customer);

}
