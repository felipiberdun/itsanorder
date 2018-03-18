package com.felipiberdun.order.repository;

import com.felipiberdun.order.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
