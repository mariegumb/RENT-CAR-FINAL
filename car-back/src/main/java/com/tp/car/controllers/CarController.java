package com.tp.car.controllers;

import com.tp.car.classes.Dates;
import com.tp.car.entities.Car;
import com.tp.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;

        Car car1 = new Car("ET-901-HJ", 5, "Ferrari", (float) 98646);
        Car car2 = new Car("DV-820-AZ", 2, "Porsche", (float) 47864);
        Car car3 = new Car("ER-223-TY", 7, "Lambo", (float) 22993);
        Car car4 = new Car("PT-563-UH", 5, "Lambo", (float) 22993);
        Car car5 = new Car("GY-690-PD", 5, "Lambo", (float) 22993);

        this.carService.createCar(car1);
        this.carService.createCar(car2);
        this.carService.createCar(car3);
        this.carService.createCar(car4);
        this.carService.createCar(car5);
    }

    @PostMapping("/cars")
    public Car createCar(@RequestParam String plateNumber, @RequestParam String brand, @RequestParam int numberOfSeats, @RequestParam float price){
        Car newCar = new Car(plateNumber, numberOfSeats, brand, price);
        this.carService.createCar(newCar);
        return newCar;
    }

    @GetMapping("/cars")
    public Iterable<Car> allCars(){
        return this.carService.getCars();
    }

    @GetMapping("/cars/{plateNumber}")
    public Car carByPlateNumber(@PathVariable(value="plateNumber") String plateNumber){
        return this.carService.getCarByPlateNumber(plateNumber);
    }

    @DeleteMapping("/cars")
    public void deleteAllCars (){
        this.carService.deleteAllCars();
    }

    @PutMapping(value = "/cars/{plateNumber}")
    public void rent(@PathVariable("plateNumber") String plateNumber, @RequestParam String begin, @RequestParam String end){
        Dates dates = new Dates(begin, end);
        carService.rent(plateNumber, dates);
    }

    @DeleteMapping(value = "/cars/getback/{plateNumber}")
    public void getBack(@PathVariable("plateNumber") String plateNumber){
        carService.getBack(plateNumber);
    }

    @DeleteMapping(value = "/cars/{plateNumber}")
    public void deleteCar(@PathVariable("plateNumber") String plateNumber) {carService.deleteCar(plateNumber);}
}
