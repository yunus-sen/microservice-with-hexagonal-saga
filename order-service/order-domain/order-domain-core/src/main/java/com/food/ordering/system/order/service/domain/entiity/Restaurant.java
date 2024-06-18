package com.food.ordering.system.order.service.domain.entiity;

import com.food.ordering.system.domain.enitity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.ResturantId;

import java.util.List;

public class Restaurant extends AggregateRoot<ResturantId> {
    private final List<Product> products;
    private final boolean active;

    private Restaurant(Builder builder) {
        super.setId(builder.restaurantId);
        products = builder.products;
        active = builder.active;
    }


    public List<Product> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    public static final class Builder {
        private ResturantId restaurantId;
        private List<Product> products;
        private boolean active;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder restaurantId(ResturantId val) {
            restaurantId = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
