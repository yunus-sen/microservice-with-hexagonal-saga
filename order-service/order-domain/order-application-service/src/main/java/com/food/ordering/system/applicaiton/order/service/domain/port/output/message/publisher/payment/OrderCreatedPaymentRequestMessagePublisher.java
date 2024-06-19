package com.food.ordering.system.applicaiton.order.service.domain.port.output.message.publisher.payment;

import com.food.ordering.system.applicaiton.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
