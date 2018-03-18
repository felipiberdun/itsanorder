package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Cousine;
import com.felipiberdun.order.domain.Store;

import java.util.List;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface CousineService {

    List<Cousine> find();

    Optional<Cousine> findById(final Long id);

    List<Store> findStoresByCousine(final Long cousineId);

}
