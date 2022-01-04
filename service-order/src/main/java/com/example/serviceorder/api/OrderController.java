package com.example.serviceorder.api;

import com.example.serviceorder.api.dto.OrderDto;
import com.example.serviceorder.repo.model.Order;
import com.example.serviceorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> index(){
        final List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> show(@PathVariable long id){
        try {
            final Order order = orderService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Order>> showWithClient(@PathVariable long id){
        final List<Order> orders = orderService.getOrderByClient(id);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<Order>> showWithEvent(@PathVariable long id){
        final List<Order> orders = orderService.getOrderByEvent(id);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody OrderDto newOrder){
        final String orderPassword = newOrder.getOrderPassword();
        final long clientID = newOrder.getClientId();
        final long eventId = newOrder.getEventId();
        final LocalDate orderDate = newOrder.getOrderDate();

        final long id = orderService.addOrder(orderPassword,clientID,eventId,orderDate);
        String location = String.format("/order/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody OrderDto newOrder){
        final String orderPassword = newOrder.getOrderPassword();
        final long clientID = newOrder.getClientId();
        final long eventId = newOrder.getEventId();
        final LocalDate orderDate = newOrder.getOrderDate();
        try{
            orderService.updateOrderById(id, orderPassword,clientID,eventId,orderDate);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
