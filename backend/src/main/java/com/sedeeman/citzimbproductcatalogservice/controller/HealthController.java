package com.sedeeman.citzimbproductcatalogservice.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController implements HealthIndicator {

    @GetMapping("/health")
    public String checkHealth() {
        Health health = health();
        if (health.getStatus().equals(Status.UP)) {
            return "Application is up and running!";
        } else {
            return "Application is down!";
        }
    }

    @Override
    public Health health() {
        try {
            // Health check logic goes here
            return Health.up().build();

        } catch (Exception e) {
            // Log error and return DOWN status
            System.err.println("Error checking health: " + e.getMessage());
            return Health.down().build();
        }
    }
}
