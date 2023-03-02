package ru.vlubchen.gradjava.util;

import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.to.DishTo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.vlubchen.gradjava.util.RestaurantUtil.restaurants;

public class DishUtil {
    public static final List<Dish> dishes = Arrays.asList(
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), restaurants.get(0), "Грибной суп", 250),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), restaurants.get(0), "Стейк", 800),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), restaurants.get(0), "Винегрет", 150),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), restaurants.get(0), "Компот", 50),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), restaurants.get(1), "Борщ", 400),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), restaurants.get(1), "Плов", 500),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), restaurants.get(1), "Оливье", 150),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), restaurants.get(1), "Чай", 50),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), restaurants.get(2), "Грибной суп", 250),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), restaurants.get(2), "Стейк", 800),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), restaurants.get(2), "Винегрет", 150),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), restaurants.get(2), "Компот", 50),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), restaurants.get(1), "Борщ", 400),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), restaurants.get(1), "Плов", 500),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), restaurants.get(1), "Оливье", 150),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), restaurants.get(1), "Чай", 50)
    );

    public static List<DishTo> getDishesTo(List<Dish> dishes) {
        return filterByPredicate(dishes, dish -> true);
    }

    public static List<DishTo> getFilteredDishesTo(List<Dish> dishes, LocalDate day) {
        return day != null ? filterByPredicate(dishes, dish -> day.isEqual(dish.getDay())):
                filterByPredicate(dishes, dish -> true);
    }

    public static List<DishTo> filterByPredicate(Collection<Dish> dishes, Predicate<Dish> filter) {
        return dishes.stream()
                .filter(filter)
                .map(dish -> new DishTo(dish.getId(), dish.getDay(), dish.getRestaurant(), dish.getName(), dish.getPrice()))
                .collect(Collectors.toList());
    }
}