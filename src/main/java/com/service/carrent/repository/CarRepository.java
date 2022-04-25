package com.service.carrent.repository;

import com.service.carrent.model.Car;

import java.util.Optional;

public interface CarRepository {
    Optional<Car> findCarById(Long id);
}
