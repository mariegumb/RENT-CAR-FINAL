package com.tp.car.repository;

import com.tp.car.entities.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {

    Optional<Car> findByPlateNumber(String plateNumber);

}
