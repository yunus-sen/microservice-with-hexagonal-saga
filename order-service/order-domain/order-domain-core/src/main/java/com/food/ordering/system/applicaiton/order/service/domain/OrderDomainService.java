package com.food.ordering.system.applicaiton.order.service.domain;

import com.food.ordering.system.applicaiton.order.service.domain.entiity.Order;
import com.food.ordering.system.applicaiton.order.service.domain.entiity.Restaurant;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order orderi, List<String> failureMessages);
}
