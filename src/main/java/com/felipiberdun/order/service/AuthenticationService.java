package com.felipiberdun.order.service;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.domain.Token;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface AuthenticationService {

    Customer authenticate(final String email, final String password);

    Customer validate(final Token token);

}
