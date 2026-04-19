package com.example.featureflags.dto;
import com.example.featureflags.entity.Environment;

public class FeatureFlagResponse {
    
    private String feature;
    private Environment environment;
    private boolean enabled;

    public FeatureFlagResponse(String feature, Environment environment, boolean enabled) {
        this.feature = feature;
        this.environment = environment;
        this.enabled = enabled;
    }

    public String getFeature() {
        return feature;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
