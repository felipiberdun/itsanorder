package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.dto.external.CustomerDto;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface CustomerService {

    CustomerDto findById(final Long id);

    CustomerDto create(final Customer customer);

}
