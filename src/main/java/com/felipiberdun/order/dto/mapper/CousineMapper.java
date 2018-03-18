package com.felipiberdun.order.dto.mapper;

import com.felipiberdun.order.domain.Cousine;
import com.felipiberdun.order.dto.output.CousineDto;
import org.mapstruct.Mapper;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Mapper(componentModel = "spring")
public interface CousineMapper {

    CousineDto toDto(final Cousine cousine);

}
