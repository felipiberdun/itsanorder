package com.felipiberdun.order.dto;

import com.felipiberdun.order.domain.OrderStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class OrderStatusChangeRequest implements Serializable {

    @NotNull
    private OrderStatus status;
    @NotNull
    private Source source;

    public OrderStatusChangeRequest() {
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Source getSource() {
        return source;
    }

}
