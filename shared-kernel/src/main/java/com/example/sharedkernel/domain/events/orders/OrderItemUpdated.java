package com.example.sharedkernel.domain.events.orders;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class OrderItemUpdated extends DomainEvent {

    private String productId;
    private int quantity;

    public OrderItemUpdated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_UPDATED);
    }

    public OrderItemUpdated(String topic, String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_UPDATED);
        this.productId = productId;
        this.quantity = quantity;
    }
}