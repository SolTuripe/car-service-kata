package com.service.carrent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Rent {

    private Long rentingId;
    private User rentingUser;
    private Car rentedCar;

    public Rent(User rentingUser, Car rentedCar, Long rentingId) {
        this.rentingId = rentingId;
        this.rentedCar = rentedCar;
        this.rentingUser = rentingUser;
    }

    public Rent(User user, Car car) {
    }
}
