package com.Jadhav.Contest_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class LeetCodeService {

    @Autowired
    private ContestService contestService;
    private final RestTemplate restTemplate = new RestTemplate();

    // Manual fetch method for controller
    public String fetchLeetCodeContestsManually() {
        System.out.println("🚨 Manual fetch triggered for LeetCode");
        return performFetch();
    }

    // Scheduled fetch method
    @Scheduled(cron = "0 0 2 * * *") // 2:00 AM
    public void fetchLeetCodeContests() {
        System.out.println("🚨 Scheduled fetch triggered for LeetCode");
        performFetch();
    }

    // Common fetch logic
    private String performFetch() {
        String url = "https://leetcode.com/graphql";
        String query = """
        query {
          upcomingContests {
            title
            startTime
            duration
          }
        }
        """;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(Map.of("query", query), headers);

            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

            if (response.getBody() == null || !((Map<?, ?>) response.getBody()).containsKey("data")) {
                return "❌ No response from LeetCode API";
            }

            Map<String, Object> responseData = (Map<String, Object>) response.getBody().get("data");
            List<Map<String, Object>> contests = (List<Map<String, Object>>) responseData.get("upcomingContests");

            if (contests == null) {
                return "❌ No upcoming contests in LeetCode response";
            }

            System.out.println("📥 LeetCode Contests Fetched: " + contests.size());
            int count = 0;

            for (Map<String, Object> contest : contests) {
                String title = (String) contest.get("title");
                Number startNum = (Number) contest.get("startTime");
                Number durationNum = (Number) contest.get("duration");

                if (startNum == null || durationNum == null) continue;

                long startTime = startNum.longValue();
                long duration = durationNum.longValue();

                Date start = new Date(startTime * 1000);
                Date end = new Date((startTime + duration) * 1000);
                String id = "leetcode-" + startTime;
                String slug = title.toLowerCase().replaceAll("[^a-z0-9]+", "-");
                String contestUrl = "https://leetcode.com/contest/" + slug;

                contestService.saveContestIfNotExists(title, "LeetCode", id, start, end, duration, contestUrl);
                count++;

                System.out.println("📌 Title: " + title);
                System.out.println("🗓️ Start: " + start);
                System.out.println("🕒 Duration: " + (duration / 60) + " minutes");
                System.out.println("🔗 URL: " + contestUrl);
                System.out.println("--------------------------------------");
            }

            String message = "✅ Processed " + count + " upcoming LeetCode contests";
            System.out.println(message);
            return message;

        } catch (Exception e) {
            String error = "❌ Error fetching LeetCode contests: " + e.getMessage();
            System.err.println(error);
            e.printStackTrace();
            return error;
        }
    }
}