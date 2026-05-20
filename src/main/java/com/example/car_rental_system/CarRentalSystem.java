package com.example.car_rental_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {

    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

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
            System.out.println("Car is not available.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.print("Enter Name: ");
                String customerName = scanner.nextLine();

                System.out.println("Available Cars:");

                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " "
                                + car.getBrand() + " "
                                + car.getModel());
                    }
                }

                System.out.print("Enter Car ID: ");
                String carId = scanner.nextLine();

                System.out.print("Enter Rental Days: ");
                int days = scanner.nextInt();
                scanner.nextLine();

                Customer customer = new Customer(
                        "CUS" + (customers.size() + 1),
                        customerName);

                addCustomer(customer);

                Car selectedCar = null;

                for (Car car : cars) {
                    if (car.getCarId().equals(carId)
                            && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {

                    double totalPrice =
                            selectedCar.calculatePrice(days);

                    System.out.println("Total Price: $" + totalPrice);

                    rentCar(selectedCar, customer, days);

                    System.out.println("Car Rented Successfully");

                } else {
                    System.out.println("Invalid Car ID");
                }

            } else if (choice == 2) {

                System.out.print("Enter Car ID to Return: ");
                String carId = scanner.nextLine();

                for (Car car : cars) {
                    if (car.getCarId().equals(carId)) {
                        returnCar(car);
                        System.out.println("Car Returned Successfully");
                    }
                }

            } else if (choice == 3) {
                break;
            }
        }
    }
}