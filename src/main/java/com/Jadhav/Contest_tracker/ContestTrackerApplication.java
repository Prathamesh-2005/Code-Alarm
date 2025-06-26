package com.Jadhav.Contest_tracker;
import com.Jadhav.Contest_tracker.Model.Contest;
import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.Model.ReminderTime;
import com.Jadhav.Contest_tracker.Model.User;
import com.Jadhav.Contest_tracker.Repository.ContestRepository;
import com.Jadhav.Contest_tracker.Repository.ReminderRepository;
import com.Jadhav.Contest_tracker.Repository.UserRepository;
import com.Jadhav.Contest_tracker.service.EmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories("com.Jadhav.Contest_Tracker.Repository")
public class ContestTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContestTrackerApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner testReminderWithCustomMessage(
//			ContestRepository contestRepository,
//			ReminderRepository reminderRepository,
//			EmailService emailService,
//			UserRepository userRepository) {  // Add UserRepository
//
//		return args -> {
//			// Create or get a test user
//			User testUser = userRepository.findByEmail("jadhavprathamesh312@gmail.com")
//					.orElseGet(() -> {
//						User newUser = new User();
//						newUser.setEmail("jadhavprathamesh312@gmail.com");
//						newUser.setUsername("Test User");
//						// Set other required user fields
//						return userRepository.save(newUser);
//					});
//
//			// Create a test contest
//			Contest contest = new Contest();
//			contest.setContestName("Weekly LeetCode Challenge");
//			contest.setPlatform("LeetCode");
//			contest.setContestId("test-custom-" + System.currentTimeMillis());
//			contest.setContestUrl("https://leetcode.com/contest/weekly-challenge");
//
//			// Set start time 1 minute from now and duration of 90 minutes
//			Date now = new Date();
//			Date startDate = new Date(now.getTime() + 60 * 1000); // 1 minute from now
//			Date endDate = new Date(now.getTime() + 91 * 60 * 1000); // 91 minutes from now
//
//			contest.setContestStartDate(startDate);
//			contest.setContestEndDate(endDate);
//			contest.setContestDuration(90 * 60); // 90 minutes in seconds
//
//			// Save the contest
//			contestRepository.save(contest);
//			System.out.println("✅ Test contest created: " + contest.getContestName());
//
//			// Create a reminder with custom message
//			Reminder customReminder = new Reminder();
//			customReminder.setEmail("jadhavprathamesh312@gmail.com");
//			customReminder.setReminderTime(ReminderTime.BEFORE_5_MINUTES);
//			customReminder.setContest(contest);
//			customReminder.setUser(testUser);  // Set the user
//			customReminder.setCustomMessage("Don't forget to participate in this week's challenge! "
//					+ "I've been practicing dynamic programming problems all week for this.");
//			customReminder.setSent(false);
//
//			// Save the reminder
//			reminderRepository.save(customReminder);
//			System.out.println("⏰ Reminder with custom message created");
//
//			// Test sending the email immediately (bypassing scheduler)
//			try {
//				System.out.println("✉️ Attempting to send test email...");
//				emailService.sendReminder(
//						customReminder.getEmail(),
//						contest,
//						customReminder
//				);
//				System.out.println("✅ Email sent successfully!");
//
//				// Verify in database
//				Reminder sentReminder = reminderRepository.findById(customReminder.getId()).orElseThrow();
//				if (sentReminder.isSent()) {
//					System.out.println("📬 Reminder marked as sent in database");
//				} else {
//					System.out.println("⚠️ Reminder not marked as sent in database - check your EmailService");
//				}
//
//			} catch (Exception e) {
//				System.err.println("❌ Failed to send email: " + e.getMessage());
//				e.printStackTrace();
//			}
//
//			// Print all details
//			System.out.println("\n=== Test Summary ===");
//			System.out.println("Contest: " + contest.getContestName());
//			System.out.println("Start: " + contest.getContestStartDate());
//			System.out.println("Platform: " + contest.getPlatform());
//			System.out.println("Reminder Time: " + customReminder.getReminderTime());
//			System.out.println("Custom Message: " + customReminder.getCustomMessage());
//			System.out.println("Recipient: " + customReminder.getEmail());
//		};
 //}



}
