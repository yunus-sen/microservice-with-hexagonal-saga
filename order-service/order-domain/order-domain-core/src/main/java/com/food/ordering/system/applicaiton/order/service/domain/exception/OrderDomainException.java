package com.food.ordering.system.applicaiton.order.service.domain.exception;

import com.food.ordering.system.applicaiton.domain.exception.DomainException;

public class OrderDomainException extends DomainException {
    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDomainException(String message) {
        super(message);
    }
}
