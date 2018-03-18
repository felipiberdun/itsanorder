package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.domain.Product;
import com.felipiberdun.order.repository.ProductRepository;
import com.felipiberdun.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> find() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(final Long id) {
        return Optional.ofNullable(productRepository.findOne(id));
    }

}
