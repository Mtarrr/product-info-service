package com.github.mtarrr.pis.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyWayConfiguration {
    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${pis.migration.clean}")
    private Boolean migrationClean;


    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .baselineOnMigrate(true)
                .cleanOnValidationError(true)
                .locations("classpath:db/migration")
                .dataSource(url, userName, password)
                .load();
        if (migrationClean) {
            flyway.clean();
        }
        flyway.migrate();
        return flyway;
    }
}
