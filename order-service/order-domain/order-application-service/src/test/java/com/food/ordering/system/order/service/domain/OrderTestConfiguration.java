package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.port.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.port.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMEssagePublisher;
import com.food.ordering.system.order.service.domain.port.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.port.output.repository.RestaurantRepository;
import com.food.ordering.system.order.service.domain.port.output.repository.orderRespository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.food.ordering.system")
public class OrderTestConfiguration {

    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderPaidRestaurantRequestMEssagePublisher orderPaidRestaurantRequestMEssagePublisher() {
        return Mockito.mock(OrderPaidRestaurantRequestMEssagePublisher.class);
    }

    @Bean
    public orderRespository orderRespository() {
        return Mockito.mock(orderRespository.class);
    }

    @Bean
    public CustomerRepository customerRespository() {
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public RestaurantRepository restaurantRepository() {
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
