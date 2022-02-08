package com.github.mtarrr.pis.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyWayConfiguration{
    @Value("${db.username}")
    String username;

    @Value("${db.password}")
    String password;

    @Value("${db.url}")
    String url;

    @Value("${pis.migration.clean}")
    Boolean migrationClean;


    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .baselineOnMigrate(true)
                .cleanOnValidationError(true)
                .locations("classpath:db/migration")
                .dataSource(url, username, password)
                .load();
        if (migrationClean) {
            flyway.clean();
        }
        flyway.migrate();
        return flyway;
    }
}
