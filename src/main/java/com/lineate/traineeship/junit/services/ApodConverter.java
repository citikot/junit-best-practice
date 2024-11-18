package com.lineate.traineeship.junit.services;

import com.lineate.traineeship.junit.clients.NasaApod;
import com.lineate.traineeship.junit.dto.ApodType;
import com.lineate.traineeship.junit.entities.ApodEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApodConverter {
    public static ApodEntity nasaApodToEntity(final NasaApod nasaApod, final boolean hd) {
        final ApodEntity entity = new ApodEntity();
        entity.setUrl(hd ? nasaApod.getHdUrl() : nasaApod.getUrl());
//        entity.setTitle(nasaApod.getTitle());
        entity.setHd(hd);
        entity.setDate(nasaApod.getDate());
        entity.setType(getType(nasaApod.getExplanation()));
        return entity;
    }

    private static String getType(String explanation) {
        final List<String> words = Arrays.stream(explanation.split("[\\W]"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        for (final ApodType type: ApodType.values()) {
            if (words.contains(type.name().toLowerCase())) {
                return type.name();
            }
        }
        return ApodType.undefined.name();
    }
}
