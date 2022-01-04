package com.example.serviceorder.repo;

import com.example.serviceorder.repo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByClientId(long client_id);
    List<Order> findAllByEventId(long event_id);
}
