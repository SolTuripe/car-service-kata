package com.service.carrent.repository;

import com.service.carrent.model.Car;
import com.service.carrent.model.Rent;

import java.util.Optional;

public interface RentRepository {
    Optional<Rent> findRentByCar(Car car);
    Rent save(Rent rent);
}
