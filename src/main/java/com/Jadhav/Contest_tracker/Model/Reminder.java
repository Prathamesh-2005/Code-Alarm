package com.Jadhav.Contest_tracker.Model;

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

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "reminder_time")
    private ReminderTime reminderTime;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    private boolean sent = false;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
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

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", reminderTime=" + reminderTime +
                ", contest=" + (contest != null ? contest.getContestName() : null) +
                ", sent=" + sent +
                '}';
    }
}
