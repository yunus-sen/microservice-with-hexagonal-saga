package com.food.ordering.system.customer.service.domain.event;

import com.food.ordering.system.applicaiton.domain.event.DomainEvent;
import com.food.ordering.system.customer.service.domain.entity.Customer;

import java.time.ZonedDateTime;

public class CustomerCreatedEvent implements DomainEvent<Customer> {

    private final Customer customer;

    private final ZonedDateTime createdAt;

    public CustomerCreatedEvent(Customer customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }
}