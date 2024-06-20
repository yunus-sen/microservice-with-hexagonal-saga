package com.food.ordering.system.applicaiton.order.service.domain;

import com.food.ordering.system.applicaiton.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.applicaiton.order.service.domain.port.input.message.listener.payment.PaymentResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.food.ordering.system.applicaiton.order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Service
@Validated
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    private final OrderPaymentSaga orderPaymentSaga;

    public PaymentResponseMessageListenerImpl(OrderPaymentSaga orderPaymentSaga) {
        this.orderPaymentSaga = orderPaymentSaga;
    }

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {
        OrderPaidEvent orderPaidEvent = orderPaymentSaga.process(paymentResponse);
        log.info("Publishing orderPaidEvent for id: {}", paymentResponse.getOrderId());
        orderPaidEvent.fire();
    }

    @Override
    public void paymentCanceled(PaymentResponse paymentResponse) {
        orderPaymentSaga.rollback(paymentResponse);
        log.info("Order is roll backed for order id: {} with failure message:{}",
                paymentResponse.getOrderId(),
                String.join(FAILURE_MESSAGE_DELIMITER, paymentResponse.getFailureMessages()));

    }
}
