package com.lineate.traineeship.junit.services;

import com.lineate.traineeship.junit.clients.NasaApiClient;
import com.lineate.traineeship.junit.clients.NasaApod;
import com.lineate.traineeship.junit.entities.ApodEntity;
import com.lineate.traineeship.junit.repository.ApodRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ApodGrabberService {
    private final NasaApiClient nasaApiClient;
    private final ApodRepository apodRepository;
    private final boolean hd;
    private final Clock clock;
    private final AtomicBoolean isRunning;

    public ApodGrabberService(final NasaApiClient nasaApiClient,
                              final ApodRepository apodRepository,
                              @Value("${app.apod.grabber.hd:false}") final boolean hd,
                              final Clock clock) {
        this.nasaApiClient = nasaApiClient;
        this.apodRepository = apodRepository;
        this.hd = hd;
        this.clock = clock;
        this.isRunning = new AtomicBoolean(false);
    }

    @Scheduled(cron = "${app.apod.grabber.cron:0 0 12 * * *}")
    @Transactional
    public void grab() {
        try {
            if (isRunning.getAndSet(true)) {
                // Service is already running
                return;
            }

            final LocalDate date = LocalDate.now(clock);
            if (apodRepository.findByDate(date) != null) {
                return;
            }
            final NasaApod nasaApod = nasaApiClient.getApod(date, hd);
            if (nasaApod != null) {
                final ApodEntity entity = ApodConverter.nasaApodToEntity(nasaApod, hd);
                apodRepository.save(entity);
            }
            isRunning.set(false);
        } catch (Throwable e) {
            isRunning.set(false);
            throw e;
        }
    }
}
