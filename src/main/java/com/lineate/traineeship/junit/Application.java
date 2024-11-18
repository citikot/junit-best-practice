package com.lineate.traineeship.junit;

import com.lineate.traineeship.junit.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({
        "com.lineate.traineeship.junit.clients",
        "com.lineate.traineeship.junit.services",
        "com.lineate.traineeship.junit.api"
})
@EnableJpaRepositories("com.lineate.traineeship.junit.repository")
@EntityScan("com.lineate.traineeship.junit.entities")
@EnableAutoConfiguration
@EnableScheduling
@Import(ApplicationConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
