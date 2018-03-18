package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Product;
import com.felipiberdun.order.dto.external.StoreDto;

import java.util.List;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface StoreService {

    List<StoreDto> find();

    StoreDto findById(final Long id);

    List<Product> findProducts(final Long storeId);

}
