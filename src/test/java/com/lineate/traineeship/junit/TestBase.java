package com.lineate.traineeship.junit;

import com.lineate.traineeship.junit.clients.NasaApod;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.TimeZone;

public abstract class TestBase {
    public static Clock getClock() {
        return Clock.fixed(Instant.parse("2007-12-03T10:15:30.00Z"), TimeZone.getDefault().toZoneId());
    }

    public static NasaApod getNasaApod(String title, LocalDate date, String hdUrl, String url) {
        final NasaApod nasaApod = new NasaApod();
        nasaApod.setDate(date);
        nasaApod.setExplanation("Some explanation about some star.");
        nasaApod.setHdUrl(hdUrl);
        nasaApod.setMediaType("img");
        nasaApod.setServiceVersion("v1");
        nasaApod.setTitle(title);
        nasaApod.setUrl(url);
        return nasaApod;
    }
}
