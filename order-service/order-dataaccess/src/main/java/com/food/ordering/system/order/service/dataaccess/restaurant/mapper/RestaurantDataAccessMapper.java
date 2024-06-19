package com.food.ordering.system.order.service.dataaccess.restaurant.mapper;

import com.food.ordering.system.applicaiton.domain.valueobject.Money;
import com.food.ordering.system.applicaiton.domain.valueobject.ProductId;
import com.food.ordering.system.applicaiton.domain.valueobject.RestaurantId;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Product;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.dataaccess.restaurant.exception.RestaurantDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurant =
                restaurantEntities.stream()
                        .findFirst()
                        .orElseThrow(() -> new RestaurantDataAccessException("Restaurant not found"));

        List<Product> restaurantProducts = restaurantEntities.stream()
                .map(entity -> new Product(new ProductId(entity.getProductId()), entity.getProductName(),
                        new Money(entity.getProductPrice())))
                .collect(Collectors.toList());

        return Restaurant.Builder.builder()
                .restaurantId(new RestaurantId(restaurant.getRestaurantId()))
                .products(restaurantProducts)
                .active(restaurant.getRestaurantActive())
                .build();
    }
}
