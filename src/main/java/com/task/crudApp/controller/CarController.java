package com.task.crudApp.controller;

import com.task.crudApp.model.Car;
import com.task.crudApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CarController {
    @Autowired
    private CarRepository carRepository;


    @GetMapping("/getAllCars")
    public ResponseEntity<List<Car>> getAllCars() {

        try {
            List<Car> carList = new ArrayList<>();
            carRepository.findAll().forEach(carList::add);
            if(carList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return  new ResponseEntity<>(carList,HttpStatus.OK);
        }catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCarById/{Id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long Id) {
           Optional<Car> car = carRepository.findById(Id);

           if(car.isPresent())
           {
               return  new ResponseEntity<>(car.get(),HttpStatus.OK);
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addCar")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
         Car carData = carRepository.save(car);
         return new ResponseEntity<>(carData,HttpStatus.OK);
    }

    @PostMapping("/updateCarById/{Id}")
    public ResponseEntity<Car> updateCarById(@PathVariable Long Id, @RequestBody Car newCarData){
        Optional<Car> carOptional = carRepository.findById(Id);
        if(carOptional.isPresent())
        {
            Car car = carOptional.get();
            car.setModel(newCarData.getModel());
            car.setYearProduced(newCarData.getYearProduced());

            Car updatedCar = carRepository.save(car);
            return new ResponseEntity<>(updatedCar,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/deleteCarById/{Id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long Id){
        carRepository.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
