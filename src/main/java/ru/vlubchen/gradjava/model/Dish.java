package ru.vlubchen.gradjava.model;

import java.time.LocalDate;

public class Dish extends AbstractNamedEntity {

    private LocalDate day;

    private Restaurant restaurant;

    private int price;

    public Dish(LocalDate day, Restaurant restaurant, String name, int price) {
        this(null, day, restaurant, name, price);
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

    public int getPrice() {
        return price;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                ", id=" + id +
                "day=" + day +
                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}