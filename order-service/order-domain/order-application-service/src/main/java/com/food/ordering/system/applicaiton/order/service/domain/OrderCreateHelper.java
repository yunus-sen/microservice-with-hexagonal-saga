package com.food.ordering.system.applicaiton.order.service.domain;

import com.food.ordering.system.applicaiton.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.applicaiton.order.service.domain.entiity.Order;
import com.food.ordering.system.applicaiton.order.service.domain.entiity.Restaurant;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.applicaiton.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.applicaiton.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.applicaiton.order.service.domain.port.output.repository.CustomerRepository;
import com.food.ordering.system.applicaiton.order.service.domain.port.output.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateHelper {

    private final OrderDataMapper orderDataMapper;
    private final com.food.ordering.system.applicaiton.order.service.domain.port.output.repository.orderRespository orderRespository;
    private final OrderDomainService orderDomainService;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private void saveOrder(Order order) {
        Order orderResult = orderRespository.save(order);
        if (orderResult == null) {
            log.error("Error saving order");
            throw new OrderDomainException("Error saving order");
        }
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);

        return restaurantRepository.findRestaurantInformation(restaurant).orElseThrow(() -> {
            log.error("Restaurant not found with id: {}", createOrderCommand.getRestaurantId());
            return new OrderDomainException("Restaurant not found with id: " + createOrderCommand.getRestaurantId());
        });
    }

    private void checkCustomer(UUID customerId) {
        customerRepository.findCustomer(customerId)
                .orElseThrow(() -> {
                    log.error("Customer not found with id: {}", customerId);
                    return new OrderDomainException("Customer not found");
                });
    }
}
