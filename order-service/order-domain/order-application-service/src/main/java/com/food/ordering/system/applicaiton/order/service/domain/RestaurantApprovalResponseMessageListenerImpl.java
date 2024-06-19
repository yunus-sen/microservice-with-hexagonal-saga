package com.food.ordering.system.applicaiton.order.service.domain;

import com.food.ordering.system.applicaiton.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.applicaiton.order.service.domain.port.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@AllArgsConstructor
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {
    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {

    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

    }
}
