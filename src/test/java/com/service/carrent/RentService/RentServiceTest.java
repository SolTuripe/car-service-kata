package com.service.carrent.RentService;

import com.service.carrent.exceptions.RentServiceException;
import com.service.carrent.model.Car;
import com.service.carrent.model.Rent;
import com.service.carrent.model.User;
import com.service.carrent.repository.CarRepository;
import com.service.carrent.repository.RentRepository;
import com.service.carrent.repository.UserRepository;
import com.service.carrent.service.RentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class RentServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    CarRepository carRepository;
    @Mock
    RentRepository rentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void CanRentACar() {
        //Given - stage
        User user = new User(1L, "Rosa");
        Car car = new Car(1l, "T333", "gogo");
        Rent rent = new Rent(user, car);
        RentService rentService = new RentService(userRepository, carRepository, rentRepository);

        //When - action
        Mockito.when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(carRepository.findCarById(car.getId())).thenReturn(Optional.of(car));
        Mockito.when(rentRepository.findRentByCar(car)).thenReturn(Optional.empty());
        Mockito.when(rentRepository.save(any(Rent.class))).thenReturn(rent);

        //Then - result
        assertEquals(user, userRepository.findUserById(user.getId()).get());
        assertEquals(car, carRepository.findCarById(car.getId()).get());
        assertEquals(rent, rentRepository.save(rent));

        /*var sut = rentService.rentACar(user.getId(), car.getId());

        assertThat(sut.getClass(), equalTo(rent.getClass()));
        assertThat(sut.getRentedCar(), equalTo(car));
        assertThat(sut.getRentingUser(), equalTo(user));*/
    }

    @Test
    void ShouldThrowExceptionIfUserDoesNotExist() {
        User user = new User();
        Car car = new Car();
        Rent rent = new Rent(user, car);

        var rentService = new RentService(userRepository, carRepository, rentRepository);

        Mockito.when(userRepository.findUserById(user.getId())).thenReturn(Optional.empty());
        Mockito.when(carRepository.findCarById(car.getId())).thenReturn(Optional.of(car));
        Mockito.when(rentRepository.save(any(Rent.class))).thenReturn(rent);

        RentServiceException thrown = assertThrows(RentServiceException.class, () -> rentService.rentACar(user.getId(), car.getId()));

        assertEquals("User not found", thrown.getMessage());
        assertEquals("R-101", thrown.getCode());
    }

    @Test
    void ShouldThrowExceptionIfCarDoesNotExist() {
        User user = new User();
        Car car = new Car();
        Rent rent = new Rent(user, car);

        var rentService = new RentService(userRepository, carRepository, rentRepository);

        Mockito.when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(carRepository.findCarById(car.getId())).thenReturn(Optional.empty());
        Mockito.when(rentRepository.save(rent)).thenReturn(rent);
        Mockito.when(rentRepository.findRentByCar(car)).thenReturn(Optional.empty());

        RentServiceException thrown = assertThrows(RentServiceException.class, ()-> rentService.rentACar(user.getId(), car.getId()));

        assertEquals("Car not found", thrown.getMessage());
        assertEquals("U-102", thrown.getCode());
    }
}
