package com.tp.car.service;

import com.tp.car.classes.Dates;
import com.tp.car.entities.Car;
import com.tp.car.exeption.ResourceNotFoundException;
import com.tp.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

@Service
public class CarService {

    CarRepository carRepository;
    JMSService jmsService;


    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.jmsService = new JMSService();
    }

    public Car createCar(Car car){
        this.jmsService.sendMessageToQueue("Web client is creating a new car");
        return this.carRepository.save(car);
    }

    public Iterable<Car> getCars(){
        this.jmsService.sendMessageToQueue("Web client is getting all cars");
        return this.carRepository.findAll();
    }

    public Car getCarByPlateNumber(String plateNumber){
        if(this.carRepository.findByPlateNumber(plateNumber).isPresent()) {
            this.jmsService.sendMessageToQueue("Web client is fetching car with plateNumber : " + plateNumber);
            return this.carRepository.findByPlateNumber(plateNumber).get();
        } else {
            throw new ResourceNotFoundException();
        }

    }

    public void deleteAllCars(){
        this.jmsService.sendMessageToQueue("Web client is deleting all cars");
        this.carRepository.deleteAll();
    }

    public void rent(String plateNumber, Dates dates) {
        if(this.carRepository.findByPlateNumber(plateNumber).isPresent()){
            this.jmsService.sendMessageToQueue("Web client is renting car with plateNumber : " + plateNumber);
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
            this.jmsService.sendMessageToQueue("Web client is getting back car with plateNumber : " + plateNumber);
            Car car = this.carRepository.findByPlateNumber(plateNumber).get();
            car.setRent(false);
            car.setBeginDate(null);
            car.setEndDate(null);
            this.carRepository.save(car);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public void deleteCar(String plateNumber) {
        if(this.carRepository.findByPlateNumber(plateNumber).isPresent()){
            this.jmsService.sendMessageToQueue("Web client is deleting the car with plateNumber : " + plateNumber);
            Car car = this.carRepository.findByPlateNumber(plateNumber).get();
            this.carRepository.deleteById(car.getId());
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
