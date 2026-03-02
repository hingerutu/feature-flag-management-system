package com.example.featureflags.service;

import com.example.featureflags.entity.FeatureFlag;
import com.example.featureflags.repository.FeatureFlagRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeatureFlagService {

    private final FeatureFlagRepository repository;

    public FeatureFlagService(FeatureFlagRepository repository) {
        this.repository = repository;
    }

    public boolean isFeatureEnabled(String featureName, String env) {

        if (!env.equalsIgnoreCase("DEV") && !env.equalsIgnoreCase("PROD")) {
            throw new IllegalArgumentException("Invalid environment. Use DEV or PROD");
        }

        FeatureFlag flag = repository
                .findByNameAndEnvironment(featureName, env)
                .orElseThrow(() ->
                        new IllegalArgumentException("Feature flag not found"));

        return flag.isEnabled();
    }
    
    public List<FeatureFlag> getAllFlags() {
        return repository.findAll();
    }

    public boolean toggleFeature(String featureName, String env, boolean enabled) {
        if(!env.equalsIgnoreCase("DEV") && !env.equalsIgnoreCase("PROD")) {
            throw new IllegalArgumentException("Inavalid environment. Use DEV or PROD.");
        }
            int updated = repository.updateFlag(featureName, env, enabled);

            if(updated == 0) {
                throw new IllegalArgumentException("Feature flag not found");
            } 
            
            return enabled;
    }

    public FeatureFlag createFlag(String name, String env, boolean enabled) {

        if(!env.equalsIgnoreCase("DEV") && !env.equalsIgnoreCase("PROD")) {
            throw new IllegalArgumentException("Invalid environment");
        }

        FeatureFlag flag = new FeatureFlag(name, env.toUpperCase(), enabled);

        return repository.save(flag);
    }
    
}
