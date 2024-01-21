package com.apulbere.petshop.crop;

import com.apulbere.crop.service.CriteriaOperatorService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrOpConfig {

    @Bean
    CriteriaOperatorService criteriaOperatorService(EntityManager entityManager) {
        return new CriteriaOperatorService(entityManager);
    }
}
