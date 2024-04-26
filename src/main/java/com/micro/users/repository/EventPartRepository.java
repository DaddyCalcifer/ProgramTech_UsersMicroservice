package com.micro.users.repository;

import com.micro.users.model.AccountDTO;
import com.micro.users.model.EventDTO;
import com.micro.users.model.EventPartPK;
import com.micro.users.model.EventParticipantDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventPartRepository extends JpaRepository<EventParticipantDTO, EventPartPK> {
    List<EventParticipantDTO> findAllByEventId(UUID eventId);

    @Transactional
    @Query("INSERT INTO EventParticipantDTO (id, event, account, status) \n" +
            "VALUES (:id,:event_id, :account_id, :status)")
    void joinToEvent(@Param("id") EventPartPK id, @Param("event_id") EventDTO event_id, @Param("account_id") AccountDTO account_id, @Param("status") int status);
    @Query("SELECT COUNT(*) FROM EventParticipantDTO WHERE event = :event")
    Integer getParticipantsInEventCount(@Param("event") EventDTO event);
}
