package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.domain.Store;
import com.felipiberdun.order.repository.StoreRepository;
import com.felipiberdun.order.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(final StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> find() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> findById(final Long id) {
        return Optional.ofNullable(storeRepository.findOne(id));
    }

}
