package com.micro.users.repository;

import com.micro.users.model.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountDTO, UUID> {
    @Transactional
    @Modifying
    @Query("UPDATE AccountDTO a SET a.isDeleted = true WHERE a.id = :id AND a.isDeleted = false")
    void deleteSoftById(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE AccountDTO a SET a.updatedAt = :upd WHERE a.id = :id")
    void setUpdatedAtById(@Param("id") UUID id, @Param("upd") LocalDateTime updatedAt);

    @Transactional
    @Modifying
    @Query("UPDATE AccountDTO a SET a.isDeleted = false WHERE a.id = :id AND a.isDeleted = true")
    void recoverUserById(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE AccountDTO a SET " +
            "a.firstName = :name, " +
            "a.surname = :surname, " +
            "a.patron = :patron, " +
            "a.email = :email " +
            "WHERE a.id = :id")
    void updateUser(@Param("id") UUID id,
                    @Param("name") String name,
                    @Param("surname") String surname,
                    @Param("patron") String patron,
                    @Param("email") String email);
    @Query("SELECT COUNT(*) FROM AccountDTO")
    Integer getUserCount();

    @Query("SELECT u FROM AccountDTO u WHERE isDeleted = FALSE")
    Page<AccountDTO> getActiveUsers(org.springframework.data.domain.Pageable pageable);
}