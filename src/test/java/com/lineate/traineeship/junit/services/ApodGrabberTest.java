package com.lineate.traineeship.junit.services;

import com.lineate.traineeship.junit.TestBase;
import com.lineate.traineeship.junit.clients.NasaApiClient;
import com.lineate.traineeship.junit.clients.NasaApod;
import com.lineate.traineeship.junit.entities.ApodEntity;
import com.lineate.traineeship.junit.repository.ApodRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.LocalDate;

/**
 * Example of Mockito testing.
 */
public class ApodGrabberTest extends TestBase {

    @Test
    public void testGrabber() {
        // Prepare test data
        final Clock clock = getClock();
        final LocalDate date = LocalDate.now(clock);
        final boolean hd = true;
        final NasaApod nasaApod = getNasaApod( "title", date, "hd url", "url");
        final ApodEntity apodEntity = ApodConverter.nasaApodToEntity(nasaApod, hd);

        // Create mocks
        final NasaApiClient apiClient = Mockito.mock(NasaApiClient.class);
        final ApodRepository apodRepository = Mockito.mock(ApodRepository.class);

        // Setup mocks
        Mockito.when(apodRepository.findByDate(date)).thenReturn(null);
        Mockito.when(apiClient.getApod(date, hd)).thenReturn(nasaApod);

        // Create service
        final ApodGrabberService grabberService =
                new ApodGrabberService(apiClient, apodRepository, hd, clock);

        // Run
        grabberService.grab();

        // Verify
        Mockito.verify(apodRepository).findByDate(date);
        Mockito.verify(apiClient).getApod(Mockito.any(), Mockito.eq(hd));
        Mockito.verify(apodRepository).save(apodEntity);
    }
}
