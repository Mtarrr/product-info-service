package com.github.mtarrr.pis.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("flyway.properties")
public class FlyWayConfiguration {
    /*@Value("${db.url}")
    String url;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;
    Flyway flyway = Flyway.configure().dataSource(url,username,password).load();

    public void migrate(){
        flyway.migrate();
    }*/
}
