package com.tp.car.service;

import com.tp.car.classes.Dates;
import com.tp.car.entities.Car;
import com.tp.car.exeption.ResourceNotFoundException;
import com.tp.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car){
        return this.carRepository.save(car);
    }

    public Iterable<Car> getCars(){
        return this.carRepository.findAll();
    }

    public Car getCarByPlateNumber(String plateNumber){
        if(this.carRepository.findByPlateNumber(plateNumber).isPresent()) {
            return this.carRepository.findByPlateNumber(plateNumber).get();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public void deleteAllCars(){
        this.carRepository.deleteAll();
    }

    public void rent(String plateNumber, Dates dates) {
        System.out.println("Hello");
        if(this.carRepository.findByPlateNumber(plateNumber).isPresent()){
            Car car = this.carRepository.findByPlateNumber(plateNumber).get();
            car.setRent(true);
            car.setBeginDate(dates.getBegin());
            car.setEndDate(dates.getEnd());
            this.carRepository.save(car);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public void getBack(String plateNumber) {
        if(this.carRepository.findByPlateNumber(plateNumber).isPresent()){
            Car car = this.carRepository.findByPlateNumber(plateNumber).get();
            car.setRent(false);
            car.setBeginDate(null);
            car.setEndDate(null);
            this.carRepository.save(car);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
