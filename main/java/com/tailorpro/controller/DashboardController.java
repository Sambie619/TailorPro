package com.tailorpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tailorpro.dto.DashboardDTO;
import com.tailorpro.service.DashboardService;

@RestController
@RequestMapping("/api")   // base path (clean URL)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public DashboardDTO getDashboard() {
        return dashboardService.getDashboard();
    }
}
