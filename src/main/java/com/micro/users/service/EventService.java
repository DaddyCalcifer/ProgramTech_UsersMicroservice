package com.micro.users.service;

import com.micro.users.model.*;
import com.micro.users.repository.EventPartRepository;
import com.micro.users.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventPartRepository eventPartRepository;

    public EventDTO addEvent(EventDTO event_) {
        return eventRepository.save(event_);
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<EventParticipantDTO> getAllEventPart(){
        return eventPartRepository.findAll();
    }
    public Optional<EventDTO> getEventById(UUID id) {
        return eventRepository.findById(id);
    }
    public List<EventParticipantDTO> getEventPartByEventId(UUID id) {
        return eventPartRepository.findAllByEventId(id);
    }
    public EventParticipantDTO addEventPart(EventParticipantDTO eventPart) {
        return eventPartRepository.save(eventPart);
    }
}
