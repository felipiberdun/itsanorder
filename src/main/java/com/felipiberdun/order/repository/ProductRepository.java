package com.felipiberdun.order.repository;

import com.felipiberdun.order.domain.Product;
import com.felipiberdun.order.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(final Long id);

    List<Product> findByStore(final Store store);

}
