package ru.vlubchen.gradjava.to;

import ru.vlubchen.gradjava.model.Restaurant;

import java.time.LocalDate;

public class DishTo {
    private final Integer id;

    private final LocalDate day;

    private final Restaurant restaurant;

    private final String name;

    private final int price;

    public DishTo(Integer id, LocalDate dateOfLunch, Restaurant restaurant, String name, int price) {
        this.id = id;
        this.day = dateOfLunch;
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", date=" + day +
                ", restaurant='" + restaurant + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
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
