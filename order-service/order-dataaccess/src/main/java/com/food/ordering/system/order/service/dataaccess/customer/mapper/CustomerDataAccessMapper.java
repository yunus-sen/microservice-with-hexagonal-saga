package com.food.ordering.system.order.service.dataaccess.customer.mapper;

import com.food.ordering.system.applicaiton.domain.valueobject.CustomerId;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.dataaccess.customer.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                //.username(customer.getUsername())
                //.firstName(customer.getFirstName())
                // .lastName(customer.getLastName())
                .build();
    }
}