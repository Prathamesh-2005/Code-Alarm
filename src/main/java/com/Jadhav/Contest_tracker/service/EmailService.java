package com.Jadhav.Contest_tracker.service;

import com.Jadhav.Contest_tracker.Model.Contest;
import com.Jadhav.Contest_tracker.Model.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    public void sendReminder(String toEmail, Contest contest, Reminder reminder) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Contest Reminder: " + contest.getContestName());

        String body;

        // Check if user provided a custom message
        if (reminder.getCustomMessage() != null && !reminder.getCustomMessage().trim().isEmpty()) {
            // Use custom message format
            body = "Hey!\n\n" +
                    "📝 Personal Note: " + reminder.getCustomMessage() + "\n\n" +
                    "Contest Details:\n" +
                    "📌 Name: " + contest.getContestName() + "\n" +
                    "🏢 Platform: " + contest.getPlatform() + "\n" +
                    "📅 Start Time: " + contest.getContestStartDate() + "\n" +
                    "🕒 Duration: " + (contest.getContestDuration() / 60) + " minutes\n" +
                    "🔗 Link: " + contest.getContestUrl() + "\n\n" +
                    "All the best!\n- Contest Tracker 🚀";
        } else {
            // Use default message format
            body = "Hey!\n\nThis is a reminder for the upcoming contest:\n\n" +
                    "📌 Name: " + contest.getContestName() + "\n" +
                    "🏢 Platform: " + contest.getPlatform() + "\n" +
                    "📅 Start Time: " + contest.getContestStartDate() + "\n" +
                    "🕒 Duration: " + (contest.getContestDuration() / 60) + " minutes\n" +
                    "🔗 Link: " + contest.getContestUrl() + "\n\n" +
                    "All the best!\n- CodeAlarm (Contest Tracker) 🚀";
        }

        message.setText(body);
        mailSender.send(message);
    }

    // Overloaded method for backward compatibility
    public void sendReminder(String toEmail, Contest contest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Contest Reminder: " + contest.getContestName());

        String body = "Hey!\n\nThis is a reminder for the upcoming contest:\n\n" +
                "📌 Name: " + contest.getContestName() + "\n" +
                "🏢 Platform: " + contest.getPlatform() + "\n" +
                "📅 Start Time: " + contest.getContestStartDate() + "\n" +
                "🕒 Duration: " + (contest.getContestDuration() / 60) + " minutes\n" +
                "🔗 Link: " + contest.getContestUrl() + "\n\n" +
                "All the best!\n- Contest Tracker 🚀";

        message.setText(body);
        mailSender.send(message);
    }

    public void sendPasswordResetEmail(String to, String token) {
        String resetUrl = "http://localhost:5173/reset-password?token=" + token;
        String subject = "Password Reset Request";
        String text = "To reset your password, click the link below:\n" + resetUrl +
                "\n\nThis link will expire in 24 hours.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
