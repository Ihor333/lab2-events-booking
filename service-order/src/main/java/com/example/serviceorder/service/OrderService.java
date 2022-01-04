package com.example.serviceorder.service;

import com.example.serviceorder.repo.OrderRepository;
import com.example.serviceorder.repo.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders(){return orderRepository.findAll();}

    public Order getOrderById(long id) throws IllegalArgumentException {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()) throw new IllegalArgumentException("Order doesn`t exist");
        else return order.get();
    }

    public List<Order> getOrderByClient(long id){
        return orderRepository.findAllByClientId(id);
    }

    public List<Order> getOrderByEvent(long id){
        return orderRepository.findAllByEventId(id);
    }

    public long addOrder(String orderPassword, long clientId, long eventId, LocalDate orderDate){
        final Order newOrder = new Order(orderPassword,clientId,eventId,orderDate);
        final Order addedOrder = orderRepository.save(newOrder);
        return addedOrder.getOrderId();
    }

    public void updateOrderById(long id, String orderPassword, long clientId, long eventId, LocalDate orderDate){
        final Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()) throw new IllegalArgumentException("Order doesn`t exist");
        final Order orderForUpdate = order.get();

        if (orderPassword != null && !orderPassword.isBlank()) orderForUpdate.setOrderPassword(orderPassword);
        if (clientId > 0) orderForUpdate.setClientId(clientId);
        if (eventId > 0) orderForUpdate.setEventId(eventId);
        if (orderDate != null) orderForUpdate.setOrderDate(orderDate);

        orderRepository.save(orderForUpdate);
    }

    public void deleteOrder(long id){
        orderRepository.deleteById(id);
    }

}
