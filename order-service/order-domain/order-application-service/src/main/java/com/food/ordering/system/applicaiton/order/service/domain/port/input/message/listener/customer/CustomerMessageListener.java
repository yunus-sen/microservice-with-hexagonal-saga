package com.food.ordering.system.applicaiton.order.service.domain.port.input.message.listener.customer;

import com.food.ordering.system.applicaiton.order.service.domain.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
