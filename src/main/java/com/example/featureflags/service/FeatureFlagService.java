package com.example.featureflags.service;

import com.example.featureflags.entity.FeatureFlag;
import com.example.featureflags.exception.ResourceNotFoundException;
import com.example.featureflags.entity.Environment;
import com.example.featureflags.repository.FeatureFlagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureFlagService {

    private final FeatureFlagRepository repository;

    public FeatureFlagService(FeatureFlagRepository repository) {
        this.repository = repository;
    }

    // ✅ Check if feature is enabled
    public boolean isFeatureEnabled(String featureName, Environment env) {

        FeatureFlag flag = repository
                .findByNameAndEnvironment(featureName, env)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Feature flag not found"));

        return flag.isEnabled();
    }

    // ✅ Get all flags
    public List<FeatureFlag> getAllFlags() {
        return repository.findAll();
    }

    // ✅ Create new flag
    public boolean toggleFeature(String featureName, Environment env) {

        FeatureFlag flag = repository
            .findByNameAndEnvironment(featureName, env)
            .orElseThrow(() ->
                    new IllegalArgumentException("Feature flag not found"));

        boolean newValue = !flag.isEnabled(); // 🔥 flip value
        flag.setEnabled(newValue);
        repository.save(flag);
        return newValue;
    }

    public FeatureFlag createFlag(String name, Environment env, boolean enabled) {

        FeatureFlag flag = new FeatureFlag(name, env, enabled);

        return repository.save(flag);
    }   
}