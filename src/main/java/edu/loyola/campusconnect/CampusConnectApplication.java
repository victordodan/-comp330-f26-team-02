package edu.loyola.campusconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Main entry point for the CampusConnect Spring Boot application.
  // Component scanning automatically detects controllers, services, and validators in sub-packages 
    // Controller, service, validation, model

// Convenience annotation that bumdles Configuration, EnableAutoConfiguration, and ComponentScan
@SpringBootApplication
public class CampusConnectApplication {
    public static void main(String[] args) {
      // Boots Spring application
        SpringApplication.run(CampusConnectApplication.class, args);
    }
}
