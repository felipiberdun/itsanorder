package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Customer;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface CustomerService {

    Customer findById(final Long id);

    Customer create(final Customer customer);

}
