package com.Jadhav.Contest_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CodeforcesService {

    @Autowired
    private ContestService contestService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "0 15 2 * * *") // 2:15 AM
    public void fetchCodeforcesContests() {
        System.out.println("🚨 Inside fetchCodeforcesContests()");
        String url = "https://codeforces.com/api/contest.list";
        try {
            Map response = restTemplate.getForObject(url, Map.class);
            System.out.println("🌐 Codeforces Response fetched: " + response);

            if (response == null || !"OK".equals(response.get("status")) || !response.containsKey("result")) {
                System.err.println("❌ Invalid response from Codeforces API");
                return;
            }

            List<Map<String, Object>> result = (List<Map<String, Object>>) response.get("result");
            int count = 0;

            for (Map<String, Object> contest : result) {
                if (!"BEFORE".equals(contest.get("phase"))) continue;

                String name = (String) contest.get("name");
                Integer id = (Integer) contest.get("id");
                Number startNum = (Number) contest.get("startTimeSeconds");
                Number durationNum = (Number) contest.get("durationSeconds");

                if (startNum == null || durationNum == null) {
                    System.err.println("⚠️ Missing time data for contest: " + name);
                    continue;
                }

                long start = startNum.longValue();
                long duration = durationNum.longValue();

                Date startDate = new Date(start * 1000);
                Date endDate = new Date((start + duration) * 1000);
                String contestUrl = "https://codeforces.com/contests/" + id;

                contestService.saveContestIfNotExists(name, "Codeforces", "codeforces-" + id, startDate, endDate, duration, contestUrl);
                count++;
            }
            System.out.println("✅ Processed " + count + " upcoming Codeforces contests");
        } catch (Exception e) {
            System.err.println("❌ Error fetching Codeforces contests: " + e.getMessage());
            e.printStackTrace();
        }
    }
}