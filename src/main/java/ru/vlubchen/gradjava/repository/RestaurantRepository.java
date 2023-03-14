package ru.vlubchen.gradjava.repository;

import ru.vlubchen.gradjava.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    // null if not found
    Restaurant getByName(String name);

    // ORDERED name asc
    List<Restaurant> getAll();
}