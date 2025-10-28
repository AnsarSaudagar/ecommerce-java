package com.example.ecommerce.controllers.admin;

import com.example.ecommerce.dto.DashboardDataCount;
import com.example.ecommerce.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/global")
public class GlobalController {

    @Autowired
    private GlobalService globalService;

    @GetMapping("/dashboard-counts")
    public ResponseEntity<DashboardDataCount> getDashboardDataCounts(){
        return ResponseEntity.ok(globalService.dashboardDataCounts());
    }
}
