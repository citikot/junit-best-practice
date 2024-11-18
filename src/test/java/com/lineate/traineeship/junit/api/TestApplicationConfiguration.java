package com.lineate.traineeship.junit.api;

import com.lineate.traineeship.junit.TestBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Test configuration for Spring MVC tests.
 */
@Configuration
public class TestApplicationConfiguration extends TestBase {
    @Bean
    public Clock clock() {
        return getClock();
    }
}
