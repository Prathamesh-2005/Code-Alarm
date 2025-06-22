package com.Jadhav.Contest_tracker;
import com.Jadhav.Contest_tracker.Model.Contest;
import com.Jadhav.Contest_tracker.Model.Reminder;
import com.Jadhav.Contest_tracker.Model.ReminderTime;
import com.Jadhav.Contest_tracker.Model.ReminderType;
import com.Jadhav.Contest_tracker.Repositery.ContestRepository;
import com.Jadhav.Contest_tracker.Repositery.ReminderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories("com.Jadhav.Contest_Tracker.Repositery")
public class ContestTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContestTrackerApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner testReminder(ContestRepository contestRepository, ReminderRepository reminderRepository) {
//		return args -> {
//			// Create a test contest
//			Contest contest = new Contest();
//			contest.setContestName("Test Contest");
//			contest.setPlatform("LeetCode");
//			contest.setContestId("test-" + System.currentTimeMillis());
//			contest.setContestUrl("https://leetcode.com/contest/test");
//
//			// Set the start and end date for the contest
//			Date now = new Date();
//			Date startDate = new Date(now.getTime() + 2 * 60 * 1000); // 2 minutes from now
//			Date endDate = new Date(now.getTime() + 62 * 60 * 1000); // 1 hour 2 minutes from now
//
//			contest.setContestStartDate(startDate);
//			contest.setContestEndDate(endDate);
//			contest.setContestDuration(3600);
//
//			// Save the contest to the repository
//			contestRepository.save(contest);
//
//			// Create a reminder 1 minute before the contest starts
//			Reminder emailReminder = new Reminder();
//			emailReminder.setEmail("jadhavprathamesh312@gmail.com"); // Change to the email you want to test
//			emailReminder.setReminderTime(ReminderTime.BEFORE_1_MINUTE); // Reminder time before the contest starts
//			emailReminder.setContest(contest);
//			emailReminder.setSent(false); // Initially not sent
//			reminderRepository.save(emailReminder);
//
//			// Simulate sending a notification (you can replace this part with actual email logic later)
//			System.out.println("🚀 Test Contest and Email Reminder added successfully!");
//			System.out.println("Contest: " + contest.getContestName() + " starting at " + contest.getContestStartDate());
//			System.out.println("Reminder set for: " + emailReminder.getReminderTime());
//		};



}
