package com.example.car_rental_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarRentalSystem {

    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    private static final Logger logger =
            LoggerFactory.getLogger(CarRentalSystem.class);

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            logger.info("Car is not available for rent");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
    }

    public void menu() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            logger.info("===== Car Rental System =====");
            logger.info("1. Rent a Car");
            logger.info("2. Return a Car");
            logger.info("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                logger.info("Enter Name: ");
                String customerName = scanner.nextLine();

                logger.info("Available Cars:");

                for (Car car : cars) {
                    if (car.isAvailable()) {
                        logger.info("{} {} {}",
                                car.getCarId(),
                                car.getBrand(),
                                car.getModel());
                    }
                }

                logger.info("Enter Car ID: ");
                String carId = scanner.nextLine();

                logger.info("Enter Rental Days: ");
                int days = scanner.nextInt();
                scanner.nextLine();

                Customer customer = new Customer(
                        "CUS" + (customers.size() + 1),
                        customerName
                );

                addCustomer(customer);

                Car selectedCar = null;

                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {

                    double totalPrice = selectedCar.calculatePrice(days);
                    logger.info("Total Price: ${}", totalPrice);
                    rentCar(selectedCar, customer, days);
                    logger.info("Car Rented Successfully");

                } else {
                    logger.info("Invalid Car ID");
                }

            } else if (choice == 2) {

                logger.info("Enter Car ID to Return: ");
                String carId = scanner.nextLine();

                for (Car car : cars) {
                    if (car.getCarId().equals(carId)) {
                        returnCar(car);
                        logger.info("Car Returned Successfully");
                        break;
                    }
                }

            } else if (choice == 3) {
                logger.info("Exiting System...");
                break;
            } else {
                logger.info("Invalid Choice");
            }
        }

        scanner.close();
    }
}