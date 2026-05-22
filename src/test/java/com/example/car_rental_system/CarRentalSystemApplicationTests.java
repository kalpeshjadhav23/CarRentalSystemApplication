package com.example.car_rental_system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarRentalSystemApplicationTests {


	@Test
	 void testCalculatePrice() {

		Car car = new Car("C001", "Toyota",
				"Camry", 60.0);

		double price = car.calculatePrice(5);

		assertEquals(300.0, price);
	}

	@Test
	 void testCarRent() {

		Car car = new Car("C002", "Honda",
				"Accord", 70.0);

		car.rent();

		assertFalse(car.isAvailable());
	}
	@Test
	 void testCarReturn() {

		Car car = new Car("C003", "Mahindra",
				"Thar", 150.0);

		car.rent();

		car.returnCar();

		assertTrue(car.isAvailable());
	}
}