package ru.vlubchen.gradjava.model;

import java.time.LocalDate;

public class Dish extends AbstractNamedEntity {

    private final LocalDate day;

    private final Restaurant restaurant;

    private final int price;

    public Dish(LocalDate day, Restaurant restaurant, String name, int price) {
        this(null, day, restaurant, name, price);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Dish(Integer id, LocalDate day, Restaurant restaurant, String name, int price) {
        super(id, name);
        this.day = day;
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
    }

    public LocalDate getDay() {
        return day;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}