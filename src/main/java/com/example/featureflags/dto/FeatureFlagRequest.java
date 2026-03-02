package com.example.featureflags.dto;

public class FeatureFlagRequest {
    
    private String name;
    private String environment;
    private boolean enabled;

    public String getName() {
        return name;
    }

    public String getEnvironment() {
        return environment;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
