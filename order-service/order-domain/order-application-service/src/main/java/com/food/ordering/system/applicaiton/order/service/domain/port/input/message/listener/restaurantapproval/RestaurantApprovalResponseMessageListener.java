package com.food.ordering.system.applicaiton.order.service.domain.port.input.message.listener.restaurantapproval;

import com.food.ordering.system.applicaiton.order.service.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
