package ru.vlubchen.gradjava.util;

import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.to.DishTo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DishUtil {
    public static List<DishTo> getDishesTo(List<Dish> dishes) {
        return filterByPredicate(dishes, dish -> true);
    }

    public static List<DishTo> getFilteredDishesTo(List<Dish> dishes, LocalDate day) {
        return day != null ? filterByPredicate(dishes, dish -> day.isEqual(dish.getDay())) :
                filterByPredicate(dishes, dish -> true);
    }

    public static List<DishTo> filterByPredicate(Collection<Dish> dishes, Predicate<Dish> filter) {
        return dishes.stream()
                .filter(filter)
                .map(dish -> new DishTo(dish.getId(), dish.getDay(), dish.getRestaurant(), dish.getName(), dish.getPrice()))
                .collect(Collectors.toList());
    }
}