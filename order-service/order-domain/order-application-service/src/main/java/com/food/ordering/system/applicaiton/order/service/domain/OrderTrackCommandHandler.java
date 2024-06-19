package com.food.ordering.system.applicaiton.order.service.domain;

import com.food.ordering.system.applicaiton.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.applicaiton.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Order;
import com.food.ordering.system.applicaiton.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.applicaiton.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.applicaiton.order.service.domain.port.output.repository.OrderRespository;
import com.food.ordering.system.applicaiton.order.service.domain.valueobject.TrackingId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRespository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Order order = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()))
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return orderDataMapper.orderToTrackOrderResponse(order);
    }
}
