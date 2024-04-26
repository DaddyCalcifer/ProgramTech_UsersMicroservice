package com.micro.users.controller;

import com.micro.users.model.*;
import com.micro.users.service.AccountService;
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
import java.util.Optional;
import java.util.UUID;

@RestController
public class EventController {
    private final EventService eventService;
    private final AccountService accountService;
    @Autowired
    public EventController(EventService eventService, AccountService accountService)
    {
        this.eventService = eventService;
        this.accountService = accountService;
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
    @GetMapping("/events/participants/{id}")
    public ResponseEntity<List<EventParticipantDTO>> getEventPartsById(@PathVariable UUID id) {
        List<EventParticipantDTO> events = eventService.getEventPartByEventId(id);

        if (events.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(events);
        }
    }
    @GetMapping("/events/participants")
    public ResponseEntity<List<EventParticipantDTO>> getAllEventParts() {
        List<EventParticipantDTO> events = eventService.getAllEventPart();

        if (events.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(events);
        }
    }
    @PostMapping("/events/participants:join/{event}:{acc}:{status}")
    public ResponseEntity<EventParticipantDTO> addEvent(@PathVariable UUID event,@PathVariable UUID acc, @PathVariable int status) {
        Optional<EventDTO> event_ = eventService.getEventById(event);
        EventDTO eventt;
        if (event_.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            eventt = event_.get();
        }
        Optional<AccountDTO> acc_ = accountService.getUserById(acc);
        AccountDTO accc;
        if (acc_.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            accc = acc_.get();
        }
        EventPartPK pk = new EventPartPK(eventt.getId(),accc.getId());
        EventParticipantDTO dto = new EventParticipantDTO();
        dto.setId(pk);
        dto.setEvent(eventt);
        dto.setAccount(accc);
        dto.setStatus(status);
        eventService.addEventPart(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @GetMapping("/events/participants:count/{id}")
    public ResponseEntity<Integer> getPartCountInEvent(@PathVariable UUID id) {
        Integer count = eventService.getPartCountInEvent(id);

        return ResponseEntity.ok(count);
    }
}
