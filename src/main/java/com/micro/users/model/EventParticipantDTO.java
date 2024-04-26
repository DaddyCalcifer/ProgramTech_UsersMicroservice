package com.micro.users.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "event_participants")
@Data
public class EventParticipantDTO {

    @EmbeddedId
    private EventPartPK id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private EventDTO event;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private AccountDTO account;

    @Column(name = "status")
    private int status;
}
