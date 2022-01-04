package com.example.serviceorder.repo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private String orderPassword;
    private long clientId;
    private long eventId;
    private LocalDate orderDate;

    public Order(){}

    public Order(String orderPassword, long clientId, long eventId, LocalDate orderDate) {
        this.orderPassword = orderPassword;
        this.clientId = clientId;
        this.eventId = eventId;
        this.orderDate = orderDate;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderPassword() {
        return orderPassword;
    }

    public void setOrderPassword(String orderPassword) {
        this.orderPassword = orderPassword;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
