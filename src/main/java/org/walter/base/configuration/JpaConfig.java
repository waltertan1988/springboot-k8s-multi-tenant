package org.walter.base.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan({"org.walter.base.entity", "org.walter.app.entity"})
@EnableJpaRepositories({"org.walter.base.repository", "org.walter.app.repository"})
public class JpaConfig {

}
