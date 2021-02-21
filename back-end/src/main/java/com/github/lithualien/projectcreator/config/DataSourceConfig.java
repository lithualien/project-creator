package com.github.lithualien.projectcreator.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Log4j2
@Configuration
public class DataSourceConfig {

    private final Environment environment;

    public DataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getProperty("datasource.driver-class-name"));
        dataSource.setUrl(getProperty("datasource.url"));
        dataSource.setUsername(getProperty("datasource.username"));
        dataSource.setPassword(getProperty("datasource.password"));
        return dataSource;
    }

    private String getProperty(String propertyName) {
        if(environment.getProperty(propertyName) != null) {
            return environment.getProperty(propertyName);
        } else {
            log.error("Could not read" + propertyName + " property.");
            return "";
        }
    }

}
