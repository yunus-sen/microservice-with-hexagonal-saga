package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.applicaiton.order.service.domain.OrderDomainService;
import com.food.ordering.system.applicaiton.order.service.domain.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
