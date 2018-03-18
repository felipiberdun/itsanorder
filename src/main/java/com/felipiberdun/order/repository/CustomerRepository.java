package com.felipiberdun.order.repository;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByOrder(final Order order);

}
