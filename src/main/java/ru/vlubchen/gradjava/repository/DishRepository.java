package ru.vlubchen.gradjava.repository;

import ru.vlubchen.gradjava.model.Dish;

import java.util.Collection;

public interface DishRepository {
    // null if not found, when updated
    Dish save(Dish dish);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    Collection<Dish> getAll();
}
