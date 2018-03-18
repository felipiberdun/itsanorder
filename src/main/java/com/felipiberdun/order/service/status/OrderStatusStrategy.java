package com.felipiberdun.order.service.status;

import com.felipiberdun.order.domain.OrderStatus;
import com.felipiberdun.order.dto.Source;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface OrderStatusStrategy {

    //Implement some logic to control the Status changing
    boolean validateTransition(final Source source, final OrderStatus orderStatus);

}
