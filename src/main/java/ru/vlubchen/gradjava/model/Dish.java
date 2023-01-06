package ru.vlubchen.gradjava.model;

import java.time.LocalDate;

public class Dish {

    private final LocalDate date;

    private final String name;

    private final Restaurant restaurant;

    private final int price;

    public Dish(LocalDate date, String name, Restaurant restaurant, int price) {
        this.date = date;
        this.name = name;
        this.restaurant = restaurant;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
