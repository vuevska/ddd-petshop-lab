package com.example.productcatalog.xport.events;

import com.example.productcatalog.domain.models.ProductId;
import com.example.productcatalog.services.ProductService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.domain.events.orders.OrderItemRemoved;
import com.example.sharedkernel.domain.events.orders.OrderItemUpdated;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductEventListener {

    private final ProductService productService;

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "productCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            OrderItemCreated event = DomainEvent.fromJson(jsonMessage,OrderItemCreated.class);
            productService.orderItemCreated(ProductId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "productCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage,OrderItemRemoved.class);
            productService.orderItemRemoved(ProductId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_UPDATED, groupId = "productCatalog")
    public void consumeOrderItemUpdated(String jsonMessage) {
        try {
            OrderItemUpdated event = DomainEvent.fromJson(jsonMessage, OrderItemUpdated.class);
            productService.orderItemUpdated(ProductId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
