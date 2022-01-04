package com.events.serviceevent.service;

import com.events.serviceevent.api.dto.OrderDto;
import com.events.serviceevent.repo.EventRepository;
import com.events.serviceevent.repo.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event getEventById(long id) throws IllegalArgumentException {
        Optional<Event> client = eventRepository.findById(id);
        if(client.isEmpty()) throw new IllegalArgumentException("Event doesn`t exist");
        else return client.get();
    }

    public long addEvent(String eventName, String destination, int duration, String description, int cost){
        final Event newEvent = new Event(eventName,destination,duration,description,cost);
        final Event addedEvent = eventRepository.save(newEvent);
        return addedEvent.getEventId();
    }

    public void updateEventById(long id, String eventName, String destination, int duration, String description, int cost){
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()) throw new IllegalArgumentException("Event doesn`t exist");
        final Event clientForUpdate = event.get();

        if (eventName != null && !eventName.isBlank()) clientForUpdate.setEventName(eventName);
        if (destination != null && !(destination.isBlank())) clientForUpdate.setDestination(destination);
        if (duration >= 0) clientForUpdate.setDuration(duration);
        if (description != null && !description.isBlank()) clientForUpdate.setDestination(description);
        if (cost >= 0) clientForUpdate.setCost(cost);
        eventRepository.save(clientForUpdate);
    }

    public void deleteEvent(long id){
        RestTemplate restTemplate = new RestTemplate();
        Optional<Event> event = eventRepository.findById(id);
        if(event.isPresent()) {
            String getOrderLink = "http://service-order:8080/order/event/" + event.get().getEventId();
            OrderDto[] orderList = restTemplate.getForObject(getOrderLink, OrderDto[].class);
            if(orderList != null) {
                for (OrderDto order : orderList) {
                    restTemplate.delete("http://service-order:8080/order/" + order.getOrderId());
                }
            }
        }
        eventRepository.deleteById(id);
    }

}
