package com.food.ordering.system.applicaiton.order.service.domain.event;

import com.food.ordering.system.applicaiton.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {

    private final DomainEventPublisher<OrderCancelledEvent> orderCanceledEventDomainEventPublisher;

    public OrderCancelledEvent(Order order, ZonedDateTime createdAt,
                               DomainEventPublisher<OrderCancelledEvent> orderCanceledEventDomainEventPublisher) {
        super(order, createdAt);
        this.orderCanceledEventDomainEventPublisher = orderCanceledEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderCanceledEventDomainEventPublisher.publish(this);
    }
}