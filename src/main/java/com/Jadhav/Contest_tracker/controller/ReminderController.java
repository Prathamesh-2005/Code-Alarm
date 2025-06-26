package com.Jadhav.Contest_tracker.controller;

import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.Model.User;
import com.Jadhav.Contest_tracker.service.ReminderService;
import com.Jadhav.Contest_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private UserService userService;

    @GetMapping("/my-reminders")
    public ResponseEntity<?> getMyReminders(HttpServletRequest request) {
        try {
            // Log the request for debugging
            System.out.println("Received request for /api/reminders/my-reminders");
            System.out.println("Authorization header: " + request.getHeader("Authorization"));

            // Get authentication details
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                System.out.println("User not authenticated");
                return ResponseEntity.status(401).body(createErrorResponse("Authentication required", 401));
            }

            // Get username from authentication
            String username = authentication.getName();
            if (username == null || username.equals("anonymousUser")) {
                System.out.println("Invalid username: " + username);
                return ResponseEntity.status(401).body(createErrorResponse("Invalid authentication", 401));
            }

            // Find user in database
            User user = userService.findByUsername(username).orElse(null);
            if (user == null) {
                System.out.println("User not found for username: " + username);
                return ResponseEntity.status(404).body(createErrorResponse("User not found", 404));
            }

            // Get reminders for user
            List<Reminder> reminders = reminderService.getUserReminders(user);
            System.out.println("Found " + reminders.size() + " reminders for user: " + username);

            // Transform reminders to response DTO
            List<Map<String, Object>> response = reminders.stream()
                    .map(reminder -> {
                        Map<String, Object> reminderMap = new HashMap<>();
                        reminderMap.put("id", reminder.getId().toString());
                        reminderMap.put("reminderTime", reminder.getReminderTime().toString());
                        reminderMap.put("sent", reminder.isSent());

                        if (reminder.getContest() != null) {
                            Map<String, Object> contestMap = new HashMap<>();
                            contestMap.put("id", reminder.getContest().getId().toString());
                            contestMap.put("name", reminder.getContest().getContestName());
                            contestMap.put("contestId", reminder.getContest().getContestId());
                            contestMap.put("platform", reminder.getContest().getPlatform());
                            contestMap.put("startDate", reminder.getContest().getContestStartDate().getTime());
                            contestMap.put("endDate", reminder.getContest().getContestEndDate() != null ?
                                    reminder.getContest().getContestEndDate().getTime() : null);
                            contestMap.put("duration", reminder.getContest().getContestDuration());
                            contestMap.put("url", reminder.getContest().getContestUrl());

                            reminderMap.put("contest", contestMap);
                        }

                        return reminderMap;
                    })
                    .collect(Collectors.toList());

            // Log successful response
            System.out.println("Returning " + response.size() + " reminders");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Log the full error
            System.err.println("Error in getMyReminders: " + e.getMessage());
            e.printStackTrace();

            // Return detailed error response
            return ResponseEntity.status(500).body(createErrorResponse(
                    "Internal server error: " + e.getMessage(),
                    500,
                    e.getClass().getSimpleName()
            ));
        }
    }

    private Map<String, Object> createErrorResponse(String message, int status) {
        return createErrorResponse(message, status, null);
    }

    private Map<String, Object> createErrorResponse(String message, int status, String errorType) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status);
        errorResponse.put("message", message);
        errorResponse.put("timestamp", System.currentTimeMillis());

        if (errorType != null) {
            errorResponse.put("error", errorType);
        }

        return errorResponse;
    }
}