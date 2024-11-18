package com.lineate.traineeship.junit.services;

import com.lineate.traineeship.junit.TestBase;
import com.lineate.traineeship.junit.clients.NasaApod;
import com.lineate.traineeship.junit.dto.ApodType;
import com.lineate.traineeship.junit.entities.ApodEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

/**
 * Examples of simple JUnit tests.
 */
public class ApodConverterTest extends TestBase {

    @Test
    public void testApodFieldsMapping_nonNullableFields_allFieldsMapped() {
        final LocalDate date = LocalDate.now();
        final String hdUrl = "http://localhost/apod/hd";
        final String title = "New star";
        final String url = "http://localhost/apod";

        final NasaApod nasaApod = getNasaApod(title, date, hdUrl, url);

        final ApodEntity apodEntity = ApodConverter.nasaApodToEntity(nasaApod, true);

        Assertions.assertAll("apodEntity",
                () -> Assertions.assertEquals(date, apodEntity.getDate()),
                () -> Assertions.assertEquals(title, apodEntity.getTitle(), "title"),
                () -> Assertions.assertEquals(hdUrl, apodEntity.getUrl()),
                () -> Assertions.assertTrue(apodEntity.isHd()),
                () -> Assertions.assertEquals(ApodType.star.name(), apodEntity.getType())
        );
    }
}
