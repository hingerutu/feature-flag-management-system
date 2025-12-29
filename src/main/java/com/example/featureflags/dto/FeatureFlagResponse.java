package com.example.featureflags.dto;

public class FeatureFlagResponse {
    
    private String feature;
    private String environment;
    private boolean enabled;

    public FeatureFlagResponse(String feature, String environment, boolean enabled) {
        this.feature = feature;
        this.environment = environment;
        this.enabled = enabled;
    }

    public String getFeature() {
        return feature;
    }

    public String getEnvironment() {
        return environment;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
