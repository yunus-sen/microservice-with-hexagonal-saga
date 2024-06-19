package com.food.ordering.system.applicaiton.order.service.domain.event;

import com.food.ordering.system.applicaiton.order.service.domain.entiity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {
    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
