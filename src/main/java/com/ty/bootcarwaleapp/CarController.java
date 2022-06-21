package com.ty.bootcarwaleapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

	@Autowired
	CarRepository carRepository;

	@PostMapping("/savecar")
	public Car saveCar(@RequestBody Car car) {
		return carRepository.save(car);
	}

	@GetMapping("/getall")
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@GetMapping("/car/{id}")
	public Car getCarById(@PathVariable int id) {
		Optional<Car> optional = carRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	@GetMapping("/deletecar/{id}")
	public String deleteCar(@PathVariable int id) {
		Optional<Car> optional = carRepository.findById(id);
		if (optional.isEmpty()) {
			return "No Id";
		} else {
			carRepository.deleteById(id);
			return "car deleted";
		}
	}
	
	@GetMapping("/update/{id}")
	public String updateCar(@PathVariable int id,@RequestBody Car car) {
		Optional< Car> optional=carRepository.findById(id);
		if(optional.isEmpty()) {
			return "no id";
		}else {
			carRepository.save(car);
			return "successfuly updated";
		}
	}
}
