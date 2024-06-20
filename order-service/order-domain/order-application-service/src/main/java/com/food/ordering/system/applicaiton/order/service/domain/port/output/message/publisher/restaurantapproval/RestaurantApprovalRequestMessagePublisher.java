package com.food.ordering.system.applicaiton.order.service.domain.port.output.message.publisher.restaurantapproval;

import com.food.ordering.system.applicaiton.order.service.domain.outbox.model.approvel.OrderApprovalOutboxMessage;
import com.food.ordering.system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface RestaurantApprovalRequestMessagePublisher {

    void publish(OrderApprovalOutboxMessage orderApprovalOutboxMessage,
                 BiConsumer<OrderApprovalOutboxMessage, OutboxStatus> outboxCallback);
}
