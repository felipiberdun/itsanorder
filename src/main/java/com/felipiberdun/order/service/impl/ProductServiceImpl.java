package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.dto.output.ProductDto;
import com.felipiberdun.order.dto.mapper.ProductMapper;
import com.felipiberdun.order.exception.EntityNotFoundException;
import com.felipiberdun.order.repository.ProductRepository;
import com.felipiberdun.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository,
                              final ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> find() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(final Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

}
