package com.Jadhav.Contest_tracker.controller;

import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.Model.User;
import com.Jadhav.Contest_tracker.service.ReminderService;
import com.Jadhav.Contest_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@CrossOrigin(origins = "*")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private UserService userService;

    @PostMapping("/set/{contestId}")
    public Reminder setReminder(@RequestBody Reminder reminder, @PathVariable String contestId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElseThrow();

        reminder.setUser(user);
        System.out.println("Received Reminder: " + reminder);
        return reminderService.saveReminder(reminder, contestId);
    }

    @GetMapping("/my-reminders")
    public List<Reminder> getMyReminders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElseThrow();

        return reminderService.getUserReminders(user);
    }

    @DeleteMapping("/{reminderId}")
    public void deleteReminder(@PathVariable String reminderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElseThrow();

        reminderService.deleteUserReminder(reminderId, user);
    }

    @GetMapping("/test")
    public String test() {
        return "Reminder Controller is working!";
    }
}