package com.felipiberdun.order.service;

import com.felipiberdun.order.dto.external.CousineDto;
import com.felipiberdun.order.dto.external.StoreDto;

import java.util.List;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface CousineService {

    List<CousineDto> find();

    CousineDto findById(final Long id);

    List<StoreDto> findStoresByCousine(final Long cousineId);

}
