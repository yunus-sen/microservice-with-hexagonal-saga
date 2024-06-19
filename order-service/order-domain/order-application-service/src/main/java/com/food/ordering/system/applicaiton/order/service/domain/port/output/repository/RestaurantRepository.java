package com.food.ordering.system.applicaiton.order.service.domain.port.output.repository;

import com.food.ordering.system.applicaiton.order.service.domain.entiity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
