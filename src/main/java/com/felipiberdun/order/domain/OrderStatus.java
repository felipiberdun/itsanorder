package com.felipiberdun.order.domain;

import java.util.stream.Stream;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public enum OrderStatus {

    CREATED(1, "Created"),
    ACCEPTED(2, "Accepted"),
    PREPARING(3, "Preparing"),
    ON_THE_WAY(4, "On the way"),
    CANCELLED(5, "Cancelled"),
    DELIVERIED(6, "Deliveried");

    private final int id;
    private final String description;

    OrderStatus(final int id, final String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus fromId(final int id) {
        return Stream.of(OrderStatus.values())
                .filter(status -> status.getId() == id)
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(OrderStatus.class, "No Order found for id " + id));
    }
}
