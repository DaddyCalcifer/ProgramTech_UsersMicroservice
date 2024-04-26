package com.micro.users.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Data
public class EventPartPK implements Serializable {

    private UUID eventId;

    private UUID accountId;
    public EventPartPK(UUID event, UUID acc)
    {
        this.eventId = event;
        this.accountId = acc;
    }
    public EventPartPK(){}
}
