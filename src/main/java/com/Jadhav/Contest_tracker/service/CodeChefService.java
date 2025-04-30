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

    @Scheduled(cron = "0 30 2 * * *") // 2:30 AM
    public void fetchCodeChefContests() {
        System.out.println("🚨 Inside fetchCodeChefContests()");
        String url = "https://www.codechef.com/api/list/contests/all?sort_by=START&sorting_order=asc&offset=0&mode=all";
        Map response = restTemplate.getForObject(url, Map.class);
        System.out.println("🌐 Response fetched: " + response);
        if (response == null || !response.containsKey("future_contests")) return;

        List<Map<String, Object>> contests = (List<Map<String, Object>>) response.get("future_contests");

        for (Map<String, Object> contest : contests) {
            // Use the correct field names from the API response
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
            } catch (ParseException e) {
                System.err.println("Error parsing CodeChef contest dates: " + e.getMessage());
                e.printStackTrace(); // Add detailed stack trace to see exactly what's failing
            }
        }
    }
}