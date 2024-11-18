package com.lineate.traineeship.junit.api;

import com.lineate.traineeship.junit.dto.ApodDto;
import com.lineate.traineeship.junit.dto.ApodType;
import com.lineate.traineeship.junit.dto.ResponseDto;
import com.lineate.traineeship.junit.dto.ResponseListDto;
import com.lineate.traineeship.junit.dto.ResponseStatus;
import com.lineate.traineeship.junit.entities.ApodEntity;
import com.lineate.traineeship.junit.repository.ApodRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/apod")
public class ApodEndpoints {
    private final ApodRepository apodRepository;
    private final Clock clock;
    private final DateTimeFormatter dateTimeFormatter;

    public ApodEndpoints(final ApodRepository apodRepository,
                         final Clock clock,
                         final DateTimeFormatter dateTimeFormatter) {
        this.apodRepository = apodRepository;
        this.clock = clock;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @GetMapping
    public ResponseDto getDailyApod() {
        final ResponseDto response = new ResponseDto();
        final ApodEntity entity = apodRepository.findByDate(LocalDate.now(clock));
        if (entity == null) {
            response.setStatus(ResponseStatus.NOT_FOUND);
        } else {
            response.setStatus(ResponseStatus.SUCCESS);
            response.setContent(getDto(entity));
        }
        return response;
    }

    @GetMapping(path = "history")
    public ResponseListDto getApodHistory(final @RequestParam("from") String fromStr,
                                          final @RequestParam("to") String toStr) {
        final LocalDate from = LocalDate.parse(fromStr, dateTimeFormatter);
        final LocalDate to = LocalDate.parse(toStr, dateTimeFormatter);
        final ResponseListDto response = new ResponseListDto();
        final List<ApodEntity> entities = apodRepository.findByDateBetween(from, to);
        if (entities.isEmpty()) {
            response.setStatus(ResponseStatus.NOT_FOUND);
            response.setContent(Collections.emptyList());
        } else {
            response.setStatus(ResponseStatus.SUCCESS);
            response.setContent(entities.stream().map(this::getDto).collect(Collectors.toList()));
        }
        return response;
    }

    private ApodDto getDto(ApodEntity entity) {
        final ApodDto dto = new ApodDto();
        dto.setDate(entity.getDate());
        dto.setHd(entity.isHd());
        dto.setType(ApodType.valueOf(entity.getType()));
        dto.setTitle(entity.getTitle());
        dto.setUrl(entity.getUrl());
        return dto;
    }
}
