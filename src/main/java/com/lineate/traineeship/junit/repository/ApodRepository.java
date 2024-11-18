package com.lineate.traineeship.junit.repository;

import com.lineate.traineeship.junit.entities.ApodEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ApodRepository extends CrudRepository<ApodEntity, Long> {
    ApodEntity findByDate(LocalDate now);

    List<ApodEntity> findByDateBetween(LocalDate from, LocalDate to);
}
