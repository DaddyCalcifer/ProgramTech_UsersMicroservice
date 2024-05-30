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
@Table(name = "accounts")
@Data
public class AccountDTO {

    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    public AccountDTO() {
        // Генерируем UUID только если он не задан вручную
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotNull
    @NotBlank
    @Column(name = "patron")
    private String patron;

    @NotNull
    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}