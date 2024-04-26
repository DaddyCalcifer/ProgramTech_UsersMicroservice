package com.micro.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "events")
@Data
public class EventDTO {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    public EventDTO() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "equipment")
    private String equipment;

    @Column(name = "seats", nullable = false)
    private int seats;

    @Column(name = "organizer", nullable = false)
    private UUID organizer;

    @Column(name = "place", nullable = true)
    private String place;
}
