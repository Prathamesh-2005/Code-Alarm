package com.Jadhav.Contest_tracker.service;

import com.Jadhav.Contest_tracker.Model.Contest;
import com.Jadhav.Contest_tracker.Repository.ContestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ContestService {

    @Autowired
    private ContestRepository contestRepository;

    @Transactional
    public void saveContestIfNotExists(String name, String platform, String contestId, Date start, Date end, long duration, String url) {
        System.out.println("🔄 Trying to save: " + contestId);
        try {
            if (!contestRepository.existsByContestId(contestId)) {
                System.out.println("➕ Contest doesn't exist, creating: " + contestId);
                Contest contest = new Contest(name, platform, contestId, start, end, duration, url);
                Contest savedContest = contestRepository.save(contest);
                System.out.println("✅ Successfully saved: " + contestId + " | Start: " + start);
            } else {
                System.out.println("⚠️ Contest already exists: " + contestId);
            }
        } catch (Exception e) {
            System.err.println("❌ Error saving contest " + contestId + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Contest> getAllContests() {
        List<Contest> contests = contestRepository.findAll();
        System.out.println("📊 Retrieved " + contests.size() + " contests from database");
        return contests;
    }

    public List<Contest> getFilteredContests(String platform, LocalDate startDate, LocalDate endDate) {
        Date start = startDate != null ? java.sql.Timestamp.valueOf(startDate.atStartOfDay()) : null;
        Date end = endDate != null ? java.sql.Timestamp.valueOf(endDate.atTime(23, 59)) : null;

        List<Contest> results;
        if (platform != null && start != null && end != null) {
            results = contestRepository.findByPlatformAndContestStartDateBetween(platform, start, end);
        } else if (platform != null) {
            results = contestRepository.findByPlatform(platform);
        } else if (start != null && end != null) {
            results = contestRepository.findByContestStartDateBetween(start, end);
        } else {
            results = contestRepository.findAll();
        }

        System.out.println("🔍 Filtered contests: " + results.size() + " matches");
        return results;
    }
}