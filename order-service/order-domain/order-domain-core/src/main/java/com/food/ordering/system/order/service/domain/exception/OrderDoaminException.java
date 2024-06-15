package com.food.ordering.system.order.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class OrderDoaminException extends DomainException {
    public OrderDoaminException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDoaminException(String message) {
        super(message);
    }
}
