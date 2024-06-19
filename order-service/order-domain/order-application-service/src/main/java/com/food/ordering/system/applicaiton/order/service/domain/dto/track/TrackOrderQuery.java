package com.food.ordering.system.applicaiton.order.service.domain.dto.track;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class TrackOrderQuery {
    @NotNull
    private final UUID orderTrackingId;
}
