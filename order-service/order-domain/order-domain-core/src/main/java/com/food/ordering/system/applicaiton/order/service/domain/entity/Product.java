package com.food.ordering.system.applicaiton.order.service.domain.entity;

import com.food.ordering.system.applicaiton.domain.enitity.BaseEntity;
import com.food.ordering.system.applicaiton.domain.valueobject.Money;
import com.food.ordering.system.applicaiton.domain.valueobject.ProductId;

import java.util.UUID;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;

    public Product(ProductId productId, String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    public Product(UUID productId) {
        super.setId(new ProductId(productId));
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
