package com.food.ordering.system.applicaiton.domain.event.publisher;

import com.food.ordering.system.applicaiton.domain.event.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent> {

    void publish(T event);
}
