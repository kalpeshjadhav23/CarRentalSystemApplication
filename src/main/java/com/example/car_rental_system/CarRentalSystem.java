package com.example.car_rental_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {

    private final List<Car> cars = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Rental> rentals = new ArrayList<>();

    private static final Logger logger =
            LoggerFactory.getLogger(CarRentalSystem.class);

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

    // ================= MENU =================
    public void menu() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            showMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> rentCarFlow(scanner);

                case 2 -> returnCarFlow(scanner);

                case 3 -> {
                    logger.info("Exiting system...");
                    scanner.close();
                    return;
                }

                default -> logger.info("Invalid choice");
            }
        }
    }

    // ================= MENU UI =================
    private void showMenu() {
        logger.info("===== Car Rental System =====");
        logger.info("1. Rent a Car");
        logger.info("2. Return a Car");
        logger.info("3. Exit");
    }

    // ================= RENT FLOW =================
    private void rentCarFlow(Scanner scanner) {

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

        Car selectedCar = findAvailableCar(carId);

        if (selectedCar == null) {
            logger.info("Invalid Car ID");
            return;
        }

        double totalPrice = selectedCar.calculatePrice(days);
        logger.info("Total Price: ${}", totalPrice);

        rentCar(selectedCar, customer, days);

        logger.info("Car Rented Successfully");
    }

    // ================= RETURN FLOW =================
    private void returnCarFlow(Scanner scanner) {

        logger.info("Enter Car ID to Return: ");
        String carId = scanner.nextLine();

        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {
                returnCar(car);
                logger.info("Car Returned Successfully");
                return;
            }
        }

        logger.info("Car not found");
    }

    // ================= HELPER =================
    private Car findAvailableCar(String carId) {

        for (Car car : cars) {
            if (car.getCarId().equals(carId) && car.isAvailable()) {
                return car;
            }
        }
        return null;
    }
}