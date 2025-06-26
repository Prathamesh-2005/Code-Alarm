package com.Jadhav.Contest_tracker.Repository;

import com.Jadhav.Contest_tracker.Model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {
    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findByUser_Id(UUID userId);
    void deleteByUser_Id(UUID userId);
}