package com.lineate.traineeship.junit.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Component
public class NasaApiClient {
    private static final Logger LOG = LoggerFactory.getLogger(NasaApiClient.class);

    private static final String APOD_URI = "/planetary/apod?date={date}&hd={hd}&api_key={apiKey}";

    private final String apiKey;
    private final RestTemplate restTemplate;
    private final String apodUrl;

    public NasaApiClient(@Value("${nasa.api.key:DEMO_KEY}") final String apiKey,
                         @Value("${nasa.api.url:http://localhost}") final String apodUrl,
                         final RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.apodUrl = apodUrl;
        this.restTemplate = restTemplate;
    }

    public NasaApod getApod(LocalDate date, boolean hd) {
        try {
            final ResponseEntity<NasaApod> response =
                    restTemplate.getForEntity(apodUrl + APOD_URI, NasaApod.class, date, hd, apiKey);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                LOG.warn("Can not get APOD: [date: {}, hd: {}]. Response status: {}.",
                        date, hd, response.getStatusCode());
                return null;
            }
        } catch (final RestClientException e) {
            LOG.error("Can not get APOD: [date: {}, hd: {}]", date, hd, e);
            return null;
        }
    }
}
