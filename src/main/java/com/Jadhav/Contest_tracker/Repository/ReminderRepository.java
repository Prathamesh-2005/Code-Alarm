package com.Jadhav.Contest_tracker.Repository;

import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {

    @Query("SELECT r FROM Reminder r WHERE r.sent = false")
    List<Reminder> findAllPendingReminders();

    List<Reminder> findByUserAndSentFalse(User user);


    @Query("SELECT r FROM Reminder r WHERE r.user.id = :userId AND r.contest.id = :contestId")
    List<Reminder> findByUserIdAndContestId(@Param("userId") UUID userId, @Param("contestId") UUID contestId);

    List<Reminder> findByUser(User user);

    // Custom query to fetch reminders with contest data
    @Query("SELECT r FROM Reminder r JOIN FETCH r.contest WHERE r.user = :user ORDER BY r.reminderTime ASC")
    List<Reminder> findByUserWithContest(@Param("user") User user);
}