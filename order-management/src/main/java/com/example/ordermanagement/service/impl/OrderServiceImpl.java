package com.example.ordermanagement.service.impl;

import com.example.ordermanagement.domain.exceptions.OrderIdNotFoundException;
import com.example.ordermanagement.domain.exceptions.OrderItemIdNotFoundException;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.model.OrderItemId;
import com.example.ordermanagement.domain.repository.OrderRepository;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import com.example.ordermanagement.service.OrderService;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.OrderItemForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderItemList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getProductId().getId(),item.getQuantity())));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotFoundException::new);
        order.addItem(orderItemForm.getProduct(),orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getProduct().getId().getId(),orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotFoundException, OrderItemIdNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotFoundException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getItems().forEach(item->order.addItem(item.getProduct(),item.getQuantity()));
        return order;
    }
}
