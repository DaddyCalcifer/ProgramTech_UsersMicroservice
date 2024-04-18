package com.micro.users.repository;

import com.micro.users.model.AccountDTO;
import com.micro.users.model.EventDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventDTO, UUID> {
}
