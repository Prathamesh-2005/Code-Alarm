package com.Jadhav.Contest_tracker.controller;

import com.Jadhav.Contest_tracker.service.LeetCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leetcode")
@CrossOrigin(origins = "https://code-alarm-contest.vercel.app/")
public class LeetCodeController {

    @Autowired
    private LeetCodeService leetCodeService;

    @GetMapping("/fetch") // Changed from POST to GET
    public String fetch() {
        return leetCodeService.fetchLeetCodeContestsManually();
    }
}
