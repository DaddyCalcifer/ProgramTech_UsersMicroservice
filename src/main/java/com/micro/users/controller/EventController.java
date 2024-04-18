package com.micro.users.controller;

import com.micro.users.model.AccountDTO;
import com.micro.users.model.EventDTO;
import com.micro.users.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService)
    {
        this.eventService = eventService;
    }
    @PostMapping("/events/add")
    public ResponseEntity<EventDTO> addEvent(@Valid @RequestBody EventDTO request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Обработка ошибок валидации
            return ResponseEntity.badRequest().build();
        }

        EventDTO createdEvent = eventService.addEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
    @GetMapping("/events")
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();

        if (events.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(events);
        }
    }
}
