package com.Jadhav.Contest_tracker.service;

import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.Model.User;

import java.util.List;

public interface ReminderService {
    Reminder saveReminder(Reminder reminder, String contestId);
    List<Reminder> getUserReminders(User user);
    void deleteUserReminder(String reminderid,User user);
}
