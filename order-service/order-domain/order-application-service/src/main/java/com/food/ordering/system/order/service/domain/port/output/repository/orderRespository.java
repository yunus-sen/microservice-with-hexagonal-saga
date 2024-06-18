package com.food.ordering.system.order.service.domain.port.output.repository;

import com.food.ordering.system.order.service.domain.entiity.Order;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface orderRespository {

    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
