package com.lineate.traineeship.junit.services;

import com.lineate.traineeship.junit.clients.NasaApod;
import com.lineate.traineeship.junit.entities.ApodEntity;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Example of JUnit parameterized test.
 */
public class ApodTypeConverterTest {

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.arguments("Sun", "Sun"),
                Arguments.arguments( "Earth", "Earth"),
                Arguments.arguments( "Moon", "Moon"),
                Arguments.arguments( "spacecraft", "spacecraft"),
                Arguments.arguments( "galaxy", "galaxy"),
                Arguments.arguments( "nebula", "nebula"),
                Arguments.arguments( "star", "star"),
                Arguments.arguments( "planet", "planet"),
                Arguments.arguments( "oops", "undefined"),
                Arguments.arguments( "Star(t) with key word.", "star"),
                Arguments.arguments( "Key word planet in the middle of sentence.", "planet"),
                Arguments.arguments( "Key word at the end of galaxy.", "galaxy"),
                Arguments.arguments( "Sun has higher priority than Earth.", "Sun"),
                Arguments.arguments( "Case insensitive sPaCeCrAfT.", "spacecraft")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void testApodTypeConverter(String explanation, String type) {
        final NasaApod nasaApod = new NasaApod();
        nasaApod.setExplanation(explanation);

        final ApodEntity apodEntity = ApodConverter.nasaApodToEntity(nasaApod, false);

        Assert.assertEquals(type, apodEntity.getType());
    }
}
