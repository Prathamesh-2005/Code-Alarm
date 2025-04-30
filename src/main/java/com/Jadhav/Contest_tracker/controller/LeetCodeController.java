package com.Jadhav.Contest_tracker.controller;

import com.Jadhav.Contest_tracker.service.LeetCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/leetcode")
public class LeetCodeController {
    @Autowired
    private LeetCodeService leetCodeService;

    @PostMapping("/fetch")
    public String fetch() {
        leetCodeService.fetchLeetCodeContests();
        return "LeetCode contests synced.";
    }
}