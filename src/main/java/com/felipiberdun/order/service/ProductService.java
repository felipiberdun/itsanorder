package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface ProductService {

    List<Product> find();

    Optional<Product> findById(final Long id);

}
