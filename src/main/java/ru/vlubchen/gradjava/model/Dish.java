package ru.vlubchen.gradjava.model;

import java.time.LocalDate;

public class Dish {
    private Integer id;

    private final LocalDate dateOfLunch;

    private final Restaurant restaurant;

    private final String nameOfDish;

    private final int price;

    public Dish(LocalDate dateOfLunch, Restaurant restaurant, String nameOfDish, int price) {
        this(null, dateOfLunch, restaurant, nameOfDish, price);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Dish(Integer id, LocalDate dateOfLunch, Restaurant restaurant, String nameOfDish, int price) {
        this.id = id;
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

    public boolean isNew() {
        return id == null;
    }
}