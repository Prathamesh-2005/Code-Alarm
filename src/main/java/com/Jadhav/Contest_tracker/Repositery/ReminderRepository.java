package com.Jadhav.Contest_tracker.Repositery;

import com.Jadhav.Contest_tracker.Model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {

    @Query("SELECT r FROM Reminder r WHERE r.sent = false")
    List<Reminder> findAllPendingReminders();
}
