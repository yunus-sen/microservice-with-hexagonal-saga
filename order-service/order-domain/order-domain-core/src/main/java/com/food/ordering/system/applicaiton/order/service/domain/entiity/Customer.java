package com.food.ordering.system.applicaiton.order.service.domain.entiity;

import com.food.ordering.system.applicaiton.domain.enitity.AggregateRoot;
import com.food.ordering.system.applicaiton.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
