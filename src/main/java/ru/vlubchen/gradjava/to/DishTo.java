package ru.vlubchen.gradjava.to;

import ru.vlubchen.gradjava.model.Restaurant;

import java.time.LocalDate;

public class DishTo {

    private final LocalDate date;

    private final String name;

    private final Restaurant restaurant;

    private final int price;

    private final int votes;

    public DishTo(LocalDate date, String name, Restaurant restaurant, int price, int votes) {
        this.date = date;
        this.name = name;
        this.restaurant = restaurant;
        this.price = price;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", restaurant='" + restaurant + '\'' +
                ", price=" + price +
                ", votes=" + votes +
                '}';
    }
}
