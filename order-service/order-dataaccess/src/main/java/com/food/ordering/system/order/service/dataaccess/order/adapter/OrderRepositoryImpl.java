package com.food.ordering.system.order.service.dataaccess.order.adapter;

import com.food.ordering.system.applicaiton.order.service.domain.entity.Order;
import com.food.ordering.system.applicaiton.order.service.domain.port.output.repository.OrderRespository;
import com.food.ordering.system.applicaiton.order.service.domain.valueobject.TrackingId;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.food.ordering.system.order.service.dataaccess.order.mapper.OrderDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.order.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRespository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderDataAccessMapper.orderToOrderEntity(order);
        return orderDataAccessMapper.orderEntityToOrder(orderJpaRepository.save(orderEntity));
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJpaRepository.findByTrackingId(trackingId.getValue())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
