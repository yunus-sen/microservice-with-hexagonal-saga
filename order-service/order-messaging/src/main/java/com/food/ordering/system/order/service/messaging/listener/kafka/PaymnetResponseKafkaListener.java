package com.food.ordering.system.order.service.messaging.listener.kafka;

import com.food.ordering.system.applicaiton.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.applicaiton.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.applicaiton.kafka.order.avro.model.PaymentStatus;
import com.food.ordering.system.applicaiton.order.service.domain.port.input.message.listener.payment.PaymentResponseMessageListener;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymnetResponseKafkaListener implements KafkaConsumer<PaymentResponseAvroModel> {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final PaymentResponseMessageListener paymentResponseMessageListener;

    @Override
    @KafkaListener(id = "${kafka-consumer-config.payment-group-id}", topics = "${order-service.payment-response-topic-name}")
    public void receive(@Payload List<PaymentResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of payment reesponses received with keys: {} and partitions: {} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(paymentResponseAvroModel -> {
            if (PaymentStatus.COMPLETED.equals(paymentResponseAvroModel.getPaymentStatus())) {
                log.info("Processing successful payment for order id : {}", paymentResponseAvroModel.getOrderId());
                paymentResponseMessageListener.paymentCompleted(orderMessagingDataMapper
                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
            } else if (PaymentStatus.CANCELLED.equals(paymentResponseAvroModel.getPaymentStatus())) {
                log.info("Processing unSuccessful payment for order id : {}", paymentResponseAvroModel.getOrderId());
                paymentResponseMessageListener.paymentCanceled(orderMessagingDataMapper
                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
            }
        });
    }
}
