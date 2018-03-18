package com.felipiberdun.order.repository;

import com.felipiberdun.order.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(final Long id);

    Optional<Customer> findByEmail(final String email);

}
