package com.felipiberdun.order.exception;

import com.felipiberdun.order.domain.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderStatusException extends BaseException {

    private final OrderStatus currentStatus;
    private final OrderStatus newStatus;

    public OrderStatusException(final OrderStatus currentStatus, final OrderStatus newStatus) {
        this.currentStatus = currentStatus;
        this.newStatus = newStatus;
    }

    @Override
    public String getMessage() {
        return String.format("It's not possible to change status from %s to %s",
                currentStatus.getDescription(), newStatus.getDescription());
    }
}
