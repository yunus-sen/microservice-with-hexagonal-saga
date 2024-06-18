package com.food.ordering.system.order.service.domain.port.output.repository;

import com.food.ordering.system.order.service.domain.entiity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRespository {

    Optional<Customer> findCustomer(UUID customerId);
}
