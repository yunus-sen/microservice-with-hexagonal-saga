package com.food.ordering.system.applicaiton.order.service.domain.exception;

import com.food.ordering.system.applicaiton.domain.exception.DomainException;

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
