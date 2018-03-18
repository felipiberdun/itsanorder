package com.felipiberdun.order.repository;

import com.felipiberdun.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
