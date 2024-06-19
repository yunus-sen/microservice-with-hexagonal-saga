package com.food.ordering.system.applicaiton.order.service.domain.entity;

import com.food.ordering.system.applicaiton.domain.enitity.AggregateRoot;
import com.food.ordering.system.applicaiton.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    public Customer() {
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
