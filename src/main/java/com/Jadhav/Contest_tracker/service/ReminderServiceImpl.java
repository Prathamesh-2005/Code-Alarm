package com.Jadhav.Contest_tracker.service;

import com.Jadhav.Contest_tracker.Model.Contest;
import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.Model.User;
import com.Jadhav.Contest_tracker.Repository.ContestRepository;
import com.Jadhav.Contest_tracker.Repository.ReminderRepository;
import com.Jadhav.Contest_tracker.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private ContestRepository contestRepository;

    @Override
    public Reminder saveReminder(Reminder reminder, String contestId) {
        Contest contest = contestRepository.findByContestId(contestId)
                .orElseThrow(() -> new RuntimeException("Contest not found with ID: " + contestId));
        reminder.setContest(contest);
        return reminderRepository.save(reminder);
    }

    @Override
    public List<Reminder> getUserReminders(User user) {
        return reminderRepository.findByUser(user);
    }

    @Override
    public void deleteUserReminder(String reminderId, User user) {
        UUID id = UUID.fromString(reminderId);
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        if (!reminder.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to delete this reminder");
        }

        reminderRepository.delete(reminder);
    }
}