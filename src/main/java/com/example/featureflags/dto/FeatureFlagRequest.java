package com.example.featureflags.dto;

import com.example.featureflags.entity.Environment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FeatureFlagRequest {

    @NotBlank(message = "Feature name cannot be empty")
    private String name;

    @NotNull(message = "Environment is required")
    private Environment environment;

    @NotNull(message = "Enabled flag is required")
    private Boolean enabled;

    public String getName() {
        return name;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Boolean isEnabled() {
        return enabled;
    }
}