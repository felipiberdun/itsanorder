package com.felipiberdun.order.repository;

import com.felipiberdun.order.domain.Cousine;
import com.felipiberdun.order.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findById(final Long id);

    List<Store> findByCousine(final Cousine cousine);

}
