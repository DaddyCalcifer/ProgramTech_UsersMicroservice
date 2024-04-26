package com.micro.users.model;

import lombok.Data;

import java.util.UUID;

@Data
public class eventPartLite {
    private UUID eventId;
    private UUID accountId;
    private int status;
}
