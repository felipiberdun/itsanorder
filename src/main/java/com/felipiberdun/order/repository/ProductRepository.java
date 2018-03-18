package com.felipiberdun.order.repository;

import com.felipiberdun.order.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
