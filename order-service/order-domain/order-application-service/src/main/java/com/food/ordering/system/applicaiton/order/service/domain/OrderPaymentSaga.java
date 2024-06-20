package com.food.ordering.system.applicaiton.order.service.domain;

import com.food.ordering.system.applicaiton.domain.event.EmptyEvent;
import com.food.ordering.system.applicaiton.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Order;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.applicaiton.order.service.domain.port.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import com.food.ordering.system.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderPaymentSaga implements SagaStep<PaymentResponse, OrderPaidEvent, EmptyEvent> {

    private final OrderSagaHelper orderSagaHelper;
    private final OrderDomainService orderDomainService;
    private final OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMEssagePublisher;

    public OrderPaymentSaga(OrderSagaHelper orderSagaHelper,
                            OrderDomainService orderDomainService,
                            OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMEssagePublisher) {
        this.orderSagaHelper = orderSagaHelper;
        this.orderDomainService = orderDomainService;
        this.orderPaidRestaurantRequestMEssagePublisher = orderPaidRestaurantRequestMEssagePublisher;
    }

    @Override
    @Transactional
    public OrderPaidEvent process(PaymentResponse data) {
        log.info("Completing payment for order with id: {}", data.getOrderId());
        Order order = orderSagaHelper.findOrder(data.getOrderId());
        OrderPaidEvent orderPaidEvent = orderDomainService.payOrder(order, orderPaidRestaurantRequestMEssagePublisher);
        orderSagaHelper.saveOrder(order);
        log.info("Order with id: {} is paid.", order.getId().getValue());
        return orderPaidEvent;
    }

    @Override
    @Transactional
    public EmptyEvent rollback(PaymentResponse data) {
        log.info("cancelling order with id: {}", data.getOrderId());
        Order order = orderSagaHelper.findOrder(data.getOrderId());
        orderDomainService.cancelOrder(order, data.getFailureMessages());
        orderSagaHelper.saveOrder(order);
        log.info("Order with id: {} is cancelled.", order.getId().getValue());
        return EmptyEvent.INSTANCE;
    }

}
