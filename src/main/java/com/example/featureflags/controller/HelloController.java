package com.example.featureflags.controller;

import com.example.featureflags.dto.FeatureFlagRequest;
import com.example.featureflags.dto.FeatureFlagResponse;
import com.example.featureflags.entity.FeatureFlag;
import com.example.featureflags.service.FeatureFlagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class HelloController {

    private final FeatureFlagService featureFlagService;

    public HelloController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }
    
    @GetMapping("/api/flags/{featureName}")
    public FeatureFlagResponse getFeatureFlag(@PathVariable String featureName, @RequestParam(name = "env", required = true) String env) {
        boolean enabled = featureFlagService.isFeatureEnabled(featureName, env);
        
        return new FeatureFlagResponse(featureName, env, enabled);
    }

    @GetMapping("/api/flags")
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

    @PutMapping("/api/flags/{featureName}/toggle")
    public FeatureFlagResponse toggleFeature(@PathVariable String featureName, @RequestParam String env, @RequestParam boolean enabled) {
        boolean updatedStatus = featureFlagService.toggleFeature(featureName, env, enabled);

        return new FeatureFlagResponse(featureName, env, updatedStatus);
    }

    @PostMapping("/api/flags")
    public FeatureFlagResponse createFlag(@RequestBody FeatureFlagRequest request) {

        FeatureFlag flag = featureFlagService.createFlag(
            request.getName(),
            request.getEnvironment(),
            request.isEnabled()
        );

        return new FeatureFlagResponse(
            flag.getName(),
            flag.getEnvironment(),
            flag.isEnabled()
        );
    }
}
