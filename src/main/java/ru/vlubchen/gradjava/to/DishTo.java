package ru.vlubchen.gradjava.to;

import ru.vlubchen.gradjava.model.Restaurant;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.Objects;

public class DishTo extends BaseTo {
    private final LocalDate day;

    private final Restaurant restaurant;

    private final String name;

    private final int price;

    @ConstructorProperties({"id", "day", "restaurant", "name", "price"})
    public DishTo(Integer id, LocalDate dateOfLunch, Restaurant restaurant, String name, int price) {
        super(id);
        this.day = dateOfLunch;
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishTo dishTo = (DishTo) o;
        return price == dishTo.price &&
                id.equals(dishTo.id) &&
                day.equals(dishTo.day) &&
                restaurant.getName().equals(dishTo.restaurant.getName()) &&
                name.equals(dishTo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, day, restaurant, name, price);
    }
}