package com.food.ordering.system.order.service.messaging.publisher.kafka;

import com.food.ordering.system.applicaiton.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.applicaiton.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.applicaiton.order.service.domain.config.OrderServiceConfigData;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.applicaiton.order.service.domain.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOrderKafkaMessagePublisher implements OrderCreatedPaymentRequestMessagePublisher {

    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;

    @Override
    public void publish(OrderCreatedEvent event) {
        String orderId = event.getOrder().getId().getValue().toString();
        log.info("Received OrderCreatedEvent for OrderId: {}", orderId);

        try {
            PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
                    .orderCreatedEventToPaymentRequestAvroModel(event);

            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
                    orderId,
                    paymentRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallBack(orderServiceConfigData.getPaymentRequestTopicName(),
                            paymentRequestAvroModel,
                            orderId,
                            "PaymentRequestAvroModel"));

            log.info("Sent PaymentRequestAvroModel to Kafka for order id: {}",
                    paymentRequestAvroModel.getOrderId());
        } catch (Exception e) {
            log.error("Error while sending message to Kafka topic: {} with message: {}",
                    orderServiceConfigData.getPaymentRequestTopicName(), event);
        }
    }
}
