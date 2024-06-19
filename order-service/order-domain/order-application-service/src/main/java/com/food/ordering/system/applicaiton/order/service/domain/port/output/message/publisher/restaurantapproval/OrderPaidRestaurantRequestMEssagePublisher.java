package com.food.ordering.system.applicaiton.order.service.domain.port.output.message.publisher.restaurantapproval;

import com.food.ordering.system.applicaiton.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMEssagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
