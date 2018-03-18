package com.felipiberdun.order.service;

import com.felipiberdun.order.dto.external.ProductDto;

import java.util.List;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface ProductService {

    List<ProductDto> find();

    ProductDto findById(final Long id);

}
