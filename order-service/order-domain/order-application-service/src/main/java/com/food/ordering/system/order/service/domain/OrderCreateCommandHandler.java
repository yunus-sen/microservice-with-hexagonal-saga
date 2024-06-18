package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.entiity.Order;
import com.food.ordering.system.order.service.domain.entiity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDoaminException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.port.output.repository.CustomerRespository;
import com.food.ordering.system.order.service.domain.port.output.repository.OrderRespository;
import com.food.ordering.system.order.service.domain.port.output.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRespository orderRespository;
    private final OrderDomainService orderDomainService;
    private final CustomerRespository customerRespository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        Order orderResult = saveOrder(order);
        log.info("Order is created with id: {}", order.getId().getValue());
        return orderDataMapper.orderToCreatedOrderResponse(orderResult);
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRespository.save(order);
        if (orderResult == null) {
            log.error("Error saving order");
            throw new OrderDoaminException("Error saving order");
        }
        return orderResult;
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);

        return restaurantRepository.findRestaurantInformation(restaurant).orElseThrow(() -> {
            log.error("Restaurant not found with id: {}", createOrderCommand.getRestaurantId());
            return new OrderDoaminException("Restaurant not found with id: " + createOrderCommand.getRestaurantId());
        });
    }

    private void checkCustomer(UUID customerId) {
        customerRespository.findCustomer(customerId)
                .orElseThrow(() -> {
                    log.error("Customer not found with id: {}", customerId);
                    return new OrderDoaminException("Customer not found");
                });
    }
}
