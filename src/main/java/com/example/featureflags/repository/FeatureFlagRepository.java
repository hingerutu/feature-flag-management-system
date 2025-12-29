package com.example.featureflags.repository;

import com.example.featureflags.entity.FeatureFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface FeatureFlagRepository extends JpaRepository<FeatureFlag, Long> {
    Optional<FeatureFlag> findByNameAndEnvironment(String name, String environment);
    
    @Transactional
    @Modifying
    @Query("""
        update FeatureFlag f
        set f.enabled = :enabled
        where f.name = :name and f.environment = :environment
    """)
    int updateFlag(String name, String environment, boolean enabled);
}
