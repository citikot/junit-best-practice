package com.lineate.traineeship.junit.services;

import com.lineate.traineeship.junit.Application;
import com.lineate.traineeship.junit.TestBase;
import com.lineate.traineeship.junit.clients.NasaApiClient;
import com.lineate.traineeship.junit.clients.NasaApod;
import com.lineate.traineeship.junit.repository.ApodRepository;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.LocalDate;

/**
 * Example of TestNG multi thread testing.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApodGrabberMultiThreadTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApodRepository apodRepository;

    private ApodGrabberService grabberService;

    @BeforeClass
    public void before() {
        // Prepare test data
        final Clock CLOCK = TestBase.getClock();
        final boolean hd = true;
        final LocalDate date = LocalDate.now(CLOCK);
        final NasaApod nasaApod = TestBase.getNasaApod( "title", date, "hd url", "url");

        NasaApiClient apiClient = Mockito.mock(NasaApiClient.class);

        // Create service
        grabberService = new ApodGrabberService(apiClient, apodRepository, hd, CLOCK);

        // Setup mocks
        Mockito.when(apiClient.getApod(date, hd)).thenReturn(nasaApod);
    }

    @Test(threadPoolSize = 10, invocationCount = 1000)
    public void testGrabber() {
        grabberService.grab();
    }

    @Test(alwaysRun = true, dependsOnMethods = {"testGrabber"})
    public void testGrabberChecks() {
        Assert.assertEquals(1, apodRepository.count());
    }
}
