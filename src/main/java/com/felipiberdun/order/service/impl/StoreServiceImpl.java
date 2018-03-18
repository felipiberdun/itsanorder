package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.domain.Product;
import com.felipiberdun.order.domain.Store;
import com.felipiberdun.order.dto.output.StoreDto;
import com.felipiberdun.order.dto.mapper.StoreMapper;
import com.felipiberdun.order.exception.EntityNotFoundException;
import com.felipiberdun.order.repository.ProductRepository;
import com.felipiberdun.order.repository.StoreRepository;
import com.felipiberdun.order.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final StoreMapper storeMapper;

    @Autowired
    public StoreServiceImpl(final StoreRepository storeRepository,
                            final ProductRepository productRepository,
                            final StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    public List<StoreDto> find() {
        return storeRepository.findAll().stream()
                .map(storeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StoreDto findById(final Long id) {
        return storeRepository.findById(id)
                .map(storeMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Product> findProducts(final Long storeId) {
        final Store store = storeRepository.findById(storeId).orElseThrow(EntityNotFoundException::new);

        return productRepository.findByStore(store);
    }

}
