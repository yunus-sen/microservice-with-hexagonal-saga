package com.food.ordering.system.applicaiton.order.service.domain.mapper;

import com.food.ordering.system.applicaiton.domain.valueobject.CustomerId;
import com.food.ordering.system.applicaiton.domain.valueobject.Money;
import com.food.ordering.system.applicaiton.domain.valueobject.PaymentOrderStatus;
import com.food.ordering.system.applicaiton.domain.valueobject.RestaurantId;
import com.food.ordering.system.applicaiton.domain.valueobject.RestaurantOrderStatus;
import com.food.ordering.system.applicaiton.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.applicaiton.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.applicaiton.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.applicaiton.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Order;
import com.food.ordering.system.applicaiton.order.service.domain.entity.OrderItem;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Product;
import com.food.ordering.system.applicaiton.order.service.domain.entity.Restaurant;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.applicaiton.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.applicaiton.order.service.domain.outbox.model.approvel.OrderApprovalEventPayload;
import com.food.ordering.system.applicaiton.order.service.domain.outbox.model.approvel.OrderApprovalEventProduct;
import com.food.ordering.system.applicaiton.order.service.domain.outbox.model.payment.OrderPaymentEventPayload;
import com.food.ordering.system.applicaiton.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.Builder.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems()
                        .stream()
                        .map(item -> new Product(item.getProductId())).collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.Builder.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .resturantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemsEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreatedOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus().name())
                .failureMessage(order.getFailureMessages())
                .build();
    }

    public OrderPaymentEventPayload orderCreatedEventToOrderPaymentEventPayload(OrderCreatedEvent orderCreatedEvent) {
        return OrderPaymentEventPayload.builder()
                .customerId(orderCreatedEvent.getOrder().getCustomerId().getValue().toString())
                .orderId(orderCreatedEvent.getOrder().getId().getValue().toString())
                .price(orderCreatedEvent.getOrder().getPrice().getAmount())
                .createdAt(orderCreatedEvent.getCreatedAt())
                .paymentOrderStatus(PaymentOrderStatus.PENDING.name())
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemsEntities(List<com.food.ordering.system.applicaiton.order.service.domain.dto.create.OrderItem> items) {
        return items.stream()
                .map(orderItem -> OrderItem.Builder.builder()
                        .product(new Product(orderItem.getProductId()))
                        .price(new Money(orderItem.getPrice()))
                        .quantity(orderItem.getQuantity())
                        .subTotal(new Money(orderItem.getSubTotal()))
                        .build()).collect(Collectors.toList());
    }

    public OrderApprovalEventPayload orderPaidEventToOrderApprovalEventPayload(OrderPaidEvent orderPaidEvent) {
        return OrderApprovalEventPayload.builder()
                .orderId(orderPaidEvent.getOrder().getId().getValue().toString())
                .restaurantId(orderPaidEvent.getOrder().getResturantId().getValue().toString())
                .restaurantOrderStatus(RestaurantOrderStatus.PAID.name())
                .products(orderPaidEvent.getOrder().getItems().stream().map(orderItem ->
                        OrderApprovalEventProduct.builder()
                                .id(orderItem.getProduct().getId().getValue().toString())
                                .quantity(orderItem.getQuantity())
                                .build()).collect(Collectors.toList()))
                .price(orderPaidEvent.getOrder().getPrice().getAmount())
                .createdAt(orderPaidEvent.getCreatedAt())
                .build();
    }

    public OrderPaymentEventPayload orderCancelledEventToOrderPaymentEventPayload(OrderCancelledEvent
                                                                                          orderCancelledEvent) {
        return OrderPaymentEventPayload.builder()
                .customerId(orderCancelledEvent.getOrder().getCustomerId().getValue().toString())
                .orderId(orderCancelledEvent.getOrder().getId().getValue().toString())
                .price(orderCancelledEvent.getOrder().getPrice().getAmount())
                .createdAt(orderCancelledEvent.getCreatedAt())
                .paymentOrderStatus(PaymentOrderStatus.CANCELLED.name())
                .build();
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }
}
