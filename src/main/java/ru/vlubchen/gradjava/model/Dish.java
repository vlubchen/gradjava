package ru.vlubchen.gradjava.model;

import java.time.LocalDate;

public class Dish {

    private final LocalDate dateOfLunch;

    private final Restaurant restaurant;

    private final String nameOfDish;

    private final int price;

    public Dish(LocalDate dateOfLunch, Restaurant restaurant, String nameOfDish, int price) {
        this.dateOfLunch = dateOfLunch;
        this.restaurant = restaurant;
        this.nameOfDish = nameOfDish;
        this.price = price;
    }

    public LocalDate getDateOfLunch() {
        return dateOfLunch;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public int getPrice() {
        return price;
    }

}
