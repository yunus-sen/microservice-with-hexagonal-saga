package com.food.ordering.system.order.service.messaging.publisher.kafka;

import com.food.ordering.system.applicaiton.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.applicaiton.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.applicaiton.order.service.domain.config.OrderServiceConfigData;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.applicaiton.order.service.domain.port.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayOrderKafkaMessagePublisher implements OrderPaidRestaurantRequestMessagePublisher {

    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer;

    @Override
    public void publish(OrderPaidEvent event) {
        String orderId = event.getOrder().getId().getValue().toString();

        try {
            RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel = orderMessagingDataMapper
                    .orderPaidEventToRestaurantApprovalRequestAvroModel(event);

            kafkaProducer.send(orderServiceConfigData.getRestaurantApprovalRequestTopicName(),
                    orderId,
                    restaurantApprovalRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallBack(orderServiceConfigData.getRestaurantApprovalRequestTopicName(),
                            restaurantApprovalRequestAvroModel,
                            orderId,
                            "RestaurantApprovalRequestAvroModel"));

            log.info("Published order paid event for order id : {}", orderId);
        } catch (Exception e) {
            log.error("Error while publishing order paid event for order id : {}", orderId, e);
        }
    }
}
