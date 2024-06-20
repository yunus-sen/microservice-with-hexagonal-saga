package com.food.ordering.system.payment.service.domain.ports.output.message.publisher;

import com.food.ordering.system.applicaiton.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {

}