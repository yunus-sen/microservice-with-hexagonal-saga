package com.food.ordering.system.applicaiton.order.service.domain.valueobject;

import com.food.ordering.system.applicaiton.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
