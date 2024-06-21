package com.food.ordering.system.customer.service.domain.exception;

import com.food.ordering.system.applicaiton.domain.exception.DomainException;

public class CustomerDomainException extends DomainException {

    public CustomerDomainException(String message) {
        super(message);
    }
}
