package com.example.car_rental_system;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @GetMapping("/CarController")
    public String carHome() {
        return "Welcome to Car Rental System";
    }
}