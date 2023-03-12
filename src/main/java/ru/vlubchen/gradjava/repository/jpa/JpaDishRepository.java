package ru.vlubchen.gradjava.repository.jpa;

import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

public class JpaDishRepository implements DishRepository {
    @Override
    public Dish save(Dish dish) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Dish get(int id) {
        return null;
    }

    @Override
    public List<Dish> getAll() {
        return null;
    }

    @Override
    public List<Dish> getByDay(LocalDate day) {
        return null;
    }
}
