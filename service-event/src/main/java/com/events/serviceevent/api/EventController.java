package com.events.serviceevent.api;

import com.events.serviceevent.api.dto.EventDto;
import com.events.serviceevent.repo.model.Event;
import com.events.serviceevent.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> index(){
        final List<Event> users = eventService.getAllEvents();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> show(@PathVariable long id){
        try {
            final Event event = eventService.getEventById(id);
            return ResponseEntity.ok(event);
        } catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EventDto newEvent){
        final String eventName = newEvent.getEventName();
        final String destination = newEvent.getDestination();
        final int duration = newEvent.getDuration();
        final String description = newEvent.getDescription();
        final int cost = newEvent.getCost();

        final long id = eventService.addEvent(eventName,destination,duration,description,cost);
        String location = String.format("/event/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody EventDto newEvent){
        final String eventName = newEvent.getEventName();
        final String destination = newEvent.getDestination();
        final int duration = newEvent.getDuration();
        final String description = newEvent.getDescription();
        final int cost = newEvent.getCost();
        try{
            eventService.updateEventById(id, eventName,destination,duration,description,cost);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
