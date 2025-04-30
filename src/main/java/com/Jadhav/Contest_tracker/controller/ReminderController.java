package com.Jadhav.Contest_tracker.controller;

import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping("/set/{contestId}")
    public Reminder setReminder(@RequestBody Reminder reminder, @PathVariable String contestId) {
        System.out.println("Received Reminder: " + reminder);
        return reminderService.saveReminder(reminder, contestId);
    }

    @GetMapping("/test")
    public String test() {
        return "Reminder Controller is working!";
    }
}
