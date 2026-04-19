package com.example.featureflags.controller;

import jakarta.validation.Valid;
import com.example.featureflags.dto.FeatureFlagRequest;
import com.example.featureflags.dto.FeatureFlagResponse;
import com.example.featureflags.entity.Environment;
import com.example.featureflags.entity.FeatureFlag;
import com.example.featureflags.service.FeatureFlagService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flags") // ✅ base path
public class FeatureFlagController {

    private final FeatureFlagService featureFlagService;

    public FeatureFlagController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    // ✅ Get feature flag
    @GetMapping("/{featureName}")
    public FeatureFlagResponse getFeatureFlag(
            @PathVariable String featureName,
            @RequestParam Environment env) {

        boolean enabled = featureFlagService.isFeatureEnabled(featureName, env);

        return new FeatureFlagResponse(featureName, env, enabled);
    }

    // ✅ Get all flags
    @GetMapping
    public List<FeatureFlagResponse> getAllFlags() {
        return featureFlagService.getAllFlags()
                .stream()
                .map(flag -> new FeatureFlagResponse(
                        flag.getName(),
                        flag.getEnvironment(),
                        flag.isEnabled()
                ))
                .toList();
    }

    // ✅ Toggle feature (FIXED)
    @PutMapping("/{featureName}/toggle")
    public boolean toggleFeature(
            @PathVariable String featureName,
            @RequestParam Environment env) {

        return featureFlagService.toggleFeature(featureName, env);
    }

    // ✅ Create feature
    @PostMapping
    public ResponseEntity<FeatureFlagResponse> createFlag(
            @Valid @RequestBody FeatureFlagRequest request) {
            
        FeatureFlag flag = featureFlagService.createFlag(
                request.getName(),
                request.getEnvironment(),
                request.isEnabled()
        );

        FeatureFlagResponse response = new FeatureFlagResponse(
                flag.getName(),
                flag.getEnvironment(),
                flag.isEnabled()
        );
    
        return ResponseEntity
                .status(HttpStatus.CREATED)   // 🔥 201
                .body(response);
    }
}