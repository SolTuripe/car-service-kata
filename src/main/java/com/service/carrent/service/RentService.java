package com.service.carrent.service;

import com.service.carrent.exceptions.RentServiceException;
import com.service.carrent.model.Car;
import com.service.carrent.model.Rent;
import com.service.carrent.model.User;
import com.service.carrent.repository.CarRepository;
import com.service.carrent.repository.RentRepository;
import com.service.carrent.repository.UserRepository;

import java.util.Date;

public class RentService {

    private UserRepository userRepository;
    private CarRepository carRepository;
    private RentRepository rentRepository;

    public RentService(UserRepository userRepository, CarRepository carRepository, RentRepository rentRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.rentRepository = rentRepository;
    }

    public Rent rentACar(Long userId, Long carId) {

        //find a user
        var userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new RentServiceException("User not found", "R-101");
        }

        //find a car
        var carOptional = carRepository.findCarById(carId);
        if(carOptional.isEmpty()) {
            throw new RentServiceException("Car not found", "U-102");
        }

        //check if car is rented already
        var rentOptional = rentRepository.findRentByCar(carOptional.get());
        if(rentOptional.isPresent()) {
            throw new RentServiceException("Car not rented", "C-103");
        }

        //create rent item
        Rent rent = new Rent(new User(), new Car());
        rent.setRentingUser(userOptional.get());
        rent.setRentedCar(carOptional.get());
        rent.setDate(new Date());

        //save rent item and return
        return rentRepository.save(rent);
    }
}
