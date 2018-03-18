package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Store;

import java.util.List;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface StoreService {

    List<Store> find();

    Optional<Store> findById(final Long id);

}
