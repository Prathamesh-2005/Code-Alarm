package com.Jadhav.Contest_tracker.service;

import com.Jadhav.Contest_tracker.Model.Reminder;

public interface ReminderService {
    Reminder saveReminder(Reminder reminder, String contestId);
}