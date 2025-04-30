package com.Jadhav.Contest_tracker.service;

import com.Jadhav.Contest_tracker.Model.Contest;
import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.Repositery.ContestRepository;
import com.Jadhav.Contest_tracker.Repositery.ReminderRepository;
import com.Jadhav.Contest_tracker.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}