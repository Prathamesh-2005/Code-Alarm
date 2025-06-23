package com.Jadhav.Contest_tracker.controller;

import com.Jadhav.Contest_tracker.service.LeetCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/leetcode")
@CrossOrigin(origins = "http://localhost:5173")
public class LeetCodeController {
    @Autowired
    private LeetCodeService leetCodeService;

    @PostMapping("/fetch")
    public String fetch() {
        leetCodeService.fetchLeetCodeContests();
        return "LeetCode contests synced.";
    }
}