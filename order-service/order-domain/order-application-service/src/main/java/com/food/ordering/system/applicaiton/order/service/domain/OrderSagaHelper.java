package com.food.ordering.system.applicaiton.order.service.domain;

import com.food.ordering.system.applicaiton.domain.valueobject.OrderId;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Order;
import com.food.ordering.system.applicaiton.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.applicaiton.order.service.domain.port.output.repository.OrderRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderSagaHelper {

    private final OrderRespository orderRepository;

    public OrderSagaHelper(OrderRespository orderRepository) {
        this.orderRepository = orderRepository;
    }

    Order findOrder(String orderId) {
        Optional<Order> orderResponse = orderRepository.findById(new OrderId(UUID.fromString(orderId)));
        if (orderResponse.isEmpty()) {
            log.error("Order with id: {} could not be found!", orderId);
            throw new OrderNotFoundException("Order with id " + orderId + " could not be found!");
        }
        return orderResponse.get();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
