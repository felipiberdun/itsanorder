package com.felipiberdun.order.repository;

import com.felipiberdun.order.domain.Cousine;
import com.felipiberdun.order.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByCousine(final Cousine cousine);

}
