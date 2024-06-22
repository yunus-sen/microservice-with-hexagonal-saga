package com.food.ordering.system.applicaiton.order.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CustomerModel {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
}