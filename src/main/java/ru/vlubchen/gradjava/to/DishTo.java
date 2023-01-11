package ru.vlubchen.gradjava.to;

import ru.vlubchen.gradjava.model.Restaurant;

import java.time.LocalDate;

public class DishTo {

    private final LocalDate dateOfLunch;

    private final Restaurant restaurant;

    private final String nameOfDish;

    private final int price;

    public DishTo(LocalDate dateOfLunch, Restaurant restaurant, String nameOfDish, int price) {
        this.dateOfLunch = dateOfLunch;
        this.restaurant = restaurant;
        this.nameOfDish = nameOfDish;
        this.price = price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "date=" + dateOfLunch +
                ", restaurant='" + restaurant + '\'' +
                ", name='" + nameOfDish + '\'' +
                ", price=" + price +
                '}';
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
