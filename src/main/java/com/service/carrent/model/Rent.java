package com.service.carrent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentingId;
    private User rentingUser;
    private Car rentedCar;
    private Date date;

    public Rent(User rentingUser, Car rentedCar, Long rentingId) {
        this.rentingId = rentingId;
        this.rentedCar = rentedCar;
        this.rentingUser = rentingUser;
        this.date = new Date();
    }

    public Rent(User user, Car car) {
    }
}
