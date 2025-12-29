package com.example.featureflags.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feature_flags")
public class FeatureFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String environment;

    @Column(nullable = false)
    private boolean enabled;

    protected FeatureFlag() {
    }

    public FeatureFlag(String name, String environment, boolean enabled) {
        this.name = name;
        this.environment = environment;
        this.enabled = enabled;
    }

    public Long getID() {
        return id;
    }

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
