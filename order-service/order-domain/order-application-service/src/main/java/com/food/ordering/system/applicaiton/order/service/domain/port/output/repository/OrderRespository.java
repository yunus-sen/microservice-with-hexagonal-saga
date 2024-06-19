package com.food.ordering.system.applicaiton.order.service.domain.port.output.repository;

import com.food.ordering.system.applicaiton.order.service.domain.entity.Order;
import com.food.ordering.system.applicaiton.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRespository {

    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}