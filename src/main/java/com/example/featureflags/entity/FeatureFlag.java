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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Environment environment;

    @Column(nullable = false)
    private boolean enabled;

    protected FeatureFlag() {
    }

    public FeatureFlag(String name, Environment environment, boolean enabled) {
        this.name = name;
        this.environment = environment;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
    this.enabled = enabled;
}

}
