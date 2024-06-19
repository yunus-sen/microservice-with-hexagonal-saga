package com.food.ordering.system.applicaiton.order.service.domain;

import com.food.ordering.system.applicaiton.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.applicaiton.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.applicaiton.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.applicaiton.order.service.domain.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderCreateHelper orderCreateHelper;
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        return orderDataMapper.orderToCreatedOrderResponse(orderCreatedEvent.getOrder(), "Order created successfully");
    }
}