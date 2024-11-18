package com.lineate.traineeship.junit.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lineate.traineeship.junit.Application;
import com.lineate.traineeship.junit.entities.ApodEntity;
import com.lineate.traineeship.junit.repository.ApodRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;

/**
 * Base class for Spring MVC tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public abstract class RestControllerTest {
    protected MockMvc mvc;

    @Autowired
    protected Clock clock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ApodRepository apodRepository;

    @Autowired
    private ObjectMapper objectMapper;


    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    protected void createApod() {
        createApod(LocalDate.now(clock));
    }

    protected void createApod(LocalDate date) {
        final ApodEntity entity = new ApodEntity();
        entity.setDate(date);
        entity.setHd(true);
        entity.setTitle("Default Apod");
        entity.setType("star");
        entity.setUrl("http://localhost/default/apod");
        apodRepository.save(entity);
    }
}
