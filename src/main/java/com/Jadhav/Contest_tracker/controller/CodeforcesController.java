package com.Jadhav.Contest_tracker.controller;

import com.Jadhav.Contest_tracker.service.CodeforcesService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/codeforces")
@CrossOrigin(origins = "http://localhost:5173")
public class CodeforcesController {

    @Autowired
    private CodeforcesService codeforcesService;

    @GetMapping("/fetch")
    public String fetch() {
        return codeforcesService.fetchCodeforcesContestsManually();
    }
}