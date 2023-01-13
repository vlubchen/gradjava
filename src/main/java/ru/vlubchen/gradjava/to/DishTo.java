package ru.vlubchen.gradjava.to;

import ru.vlubchen.gradjava.model.Restaurant;

import java.time.LocalDate;

public class DishTo {
    private final Integer id;

    private final LocalDate dateOfLunch;

    private final Restaurant restaurant;

    private final String nameOfDish;

    private final int price;

    public DishTo(Integer id, LocalDate dateOfLunch, Restaurant restaurant, String nameOfDish, int price) {
        this.id = id;
        this.dateOfLunch = dateOfLunch;
        this.restaurant = restaurant;
        this.nameOfDish = nameOfDish;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", date=" + dateOfLunch +
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
