package com.Jadhav.Contest_tracker.controller;

import com.Jadhav.Contest_tracker.Model.Contest;
import com.Jadhav.Contest_tracker.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/contests")
@CrossOrigin(origins = "http://localhost:5173")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @GetMapping("/filter")
    public List<Contest> getFilteredContests(
            @RequestParam(required = false) String platform,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return contestService.getFilteredContests(platform, startDate, endDate);
    }

    @GetMapping("/all")
    public List<Contest> getAllContests() {
        return contestService.getAllContests();
    }
}