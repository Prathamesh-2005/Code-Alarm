package com.Jadhav.Contest_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CodeChefService {

    @Autowired
    private ContestService contestService;
    private final RestTemplate restTemplate = new RestTemplate();

    // Manual fetch method for controller
    public String fetchCodeChefContestsManually() {
        System.out.println("🚨 Manual fetch triggered for CodeChef");
        return performFetch();
    }

    // Scheduled fetch method
    @Scheduled(cron = "0 30 2 * * *") // 2:30 AM
    public void fetchCodeChefContests() {
        System.out.println("🚨 Scheduled fetch triggered for CodeChef");
        performFetch();
    }

    // Common fetch logic
    private String performFetch() {
        String url = "https://www.codechef.com/api/list/contests/all?sort_by=START&sorting_order=asc&offset=0&mode=all";
        try {
            Map response = restTemplate.getForObject(url, Map.class);
            System.out.println("🌐 CodeChef Response fetched: " + response);

            if (response == null || !response.containsKey("future_contests")) {
                return "❌ No future contests found in CodeChef response";
            }

            List<Map<String, Object>> contests = (List<Map<String, Object>>) response.get("future_contests");
            int count = 0;

            for (Map<String, Object> contest : contests) {
                String code = (String) contest.get("contest_code");
                String name = (String) contest.get("contest_name");
                String startStr = (String) contest.get("contest_start_date");
                String endStr = (String) contest.get("contest_end_date");

                if (startStr == null || endStr == null) continue;

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy  HH:mm:ss", Locale.ENGLISH);
                try {
                    Date start = sdf.parse(startStr);
                    Date end = sdf.parse(endStr);
                    long duration = (end.getTime() - start.getTime()) / 1000;

                    contestService.saveContestIfNotExists(name, "CodeChef", "codechef-" + code, start, end, duration, "https://www.codechef.com/" + code);
                    count++;
                } catch (ParseException e) {
                    System.err.println("Error parsing CodeChef contest dates: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            String message = "✅ Processed " + count + " upcoming CodeChef contests";
            System.out.println(message);
            return message;

        } catch (Exception e) {
            String error = "❌ Error fetching CodeChef contests: " + e.getMessage();
            System.err.println(error);
            e.printStackTrace();
            return error;
        }
    }
}
