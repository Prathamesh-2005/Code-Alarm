package com.Jadhav.Contest_tracker.service;

import com.Jadhav.Contest_tracker.Model.Contest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReminder(String toEmail, Contest contest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Contest Reminder: " + contest.getContestName());

        String body = "Hey!\n\nThis is a reminder for the upcoming contest:\n\n" +
                "📌 Name: " + contest.getContestName() + "\n" +
                "📅 Start Time: " + contest.getContestStartDate() + "\n" +
                "🕒 Duration: " + (contest.getContestDuration() / 60) + " minutes\n" +
                "🔗 Link: " + contest.getContestUrl() + "\n\n" +
                "All the best!\n- Contest Tracker 🚀";

        message.setText(body);
        mailSender.send(message);
    }
}
