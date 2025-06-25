package com.Jadhav.Contest_tracker.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Use @JsonIgnore to prevent serialization of the user object
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore  // This prevents circular reference
    private User user;

    // Keep email for backward compatibility
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "reminder_time")
    private ReminderTime reminderTime;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    private boolean sent = false;
    private String customMessage;

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReminderTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(ReminderTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    // This method will still work for getting email
    public String getEmail() {
        return user != null ? user.getEmail() : email;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", user=" + (user != null ? user.getUsername() : null) +
                ", email='" + getEmail() + '\'' +
                ", reminderTime=" + reminderTime +
                ", contest=" + (contest != null ? contest.getContestName() : null) +
                ", sent=" + sent +
                '}';
    }
}