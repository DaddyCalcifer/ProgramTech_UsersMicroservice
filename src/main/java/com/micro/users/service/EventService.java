package com.micro.users.service;

import com.micro.users.model.AccountDTO;
import com.micro.users.model.EventDTO;
import com.micro.users.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public EventDTO addEvent(EventDTO event_) {
        return eventRepository.save(event_);
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll();
    }
}
