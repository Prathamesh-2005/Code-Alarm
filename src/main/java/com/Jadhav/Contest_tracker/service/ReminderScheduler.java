package com.Jadhav.Contest_tracker.service;

import com.Jadhav.Contest_tracker.Model.Contest;
import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.Model.ReminderTime;
import com.Jadhav.Contest_tracker.Repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReminderScheduler {

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000) // Every 1 minute
    public void checkAndSendReminders() {
        ZonedDateTime now = ZonedDateTime.now();
        List<Reminder> reminders = reminderRepository.findAllPendingReminders();

        for (Reminder reminder : reminders) {
            Contest contest = reminder.getContest();
            Date startDate = contest.getContestStartDate();
            ZonedDateTime contestStart = ZonedDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());

            ZonedDateTime scheduledReminderTime = calculateReminderTime(contestStart, reminder.getReminderTime());

            if (isWithinNextMinute(scheduledReminderTime, now)) {
                if (reminder.getEmail() != null) {
                    emailService.sendReminder(reminder.getEmail(), contest);
                    reminder.setSent(true);
                    reminderRepository.save(reminder);
                }
            }
        }
    }

    private ZonedDateTime calculateReminderTime(ZonedDateTime contestStart, ReminderTime reminderTime) {
        switch (reminderTime) {
            case BEFORE_1_MINUTE:
                return contestStart.minusMinutes(1);
            case BEFORE_5_MINUTES:
                return contestStart.minusMinutes(5);
            case BEFORE_10_MINUTES:
                return contestStart.minusMinutes(10);
            case BEFORE_15_MINUTES:
                return contestStart.minusMinutes(15);
            case BEFORE_30_MINUTES:
                return contestStart.minusMinutes(30);
            case BEFORE_1_HOUR:
                return contestStart.minusHours(1);
            case BEFORE_2_HOURS:
                return contestStart.minusHours(2);
            case BEFORE_1_DAY:
                return contestStart.minusDays(1);
            default:
                throw new IllegalArgumentException("Unsupported reminder time: " + reminderTime);
        }
    }

    private boolean isWithinNextMinute(ZonedDateTime reminderTime, ZonedDateTime now) {
        Duration duration = Duration.between(now, reminderTime);
        return !duration.isNegative() && duration.toMinutes() == 0;
    }
}
