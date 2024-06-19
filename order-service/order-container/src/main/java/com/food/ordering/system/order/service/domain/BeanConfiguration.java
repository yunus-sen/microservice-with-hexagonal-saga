package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.applicaiton.order.service.domain.OrderDomainService;
import com.food.ordering.system.applicaiton.order.service.domain.OrderDomainServiceImpl;
import com.food.ordering.system.applicaiton.order.service.domain.RestaurantApprovalResponseMessageListenerImpl;
import com.food.ordering.system.applicaiton.order.service.domain.port.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }

    //todo:this will remove.
    @Bean
    public RestaurantApprovalResponseMessageListener restaurantApprovalResponseMessageListener() {
        return new RestaurantApprovalResponseMessageListenerImpl();
    }
}
