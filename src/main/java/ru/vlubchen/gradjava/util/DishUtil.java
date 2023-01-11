package ru.vlubchen.gradjava.util;

import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.model.Restaurant;
import ru.vlubchen.gradjava.to.DishTo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DishUtil {
    public static final List<Dish> dishes = Arrays.asList(
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), Restaurant.BeerHouse, "Грибной суп", 250),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), Restaurant.BeerHouse, "Стейк", 800),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), Restaurant.BeerHouse, "Винегрет", 150),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), Restaurant.BeerHouse, "Компот", 50),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), Restaurant.Sholly, "Борщ", 400),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), Restaurant.Sholly, "Плов", 500),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), Restaurant.Sholly, "Оливье", 150),
            new Dish(LocalDate.of(2023, Month.JANUARY, 9), Restaurant.Sholly, "Чай", 50),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), Restaurant.BeerHouse, "Грибной суп", 250),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), Restaurant.BeerHouse, "Стейк", 800),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), Restaurant.BeerHouse, "Винегрет", 150),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), Restaurant.BeerHouse, "Компот", 50),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), Restaurant.Sholly, "Борщ", 400),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), Restaurant.Sholly, "Плов", 500),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), Restaurant.Sholly, "Оливье", 150),
            new Dish(LocalDate.of(2023, Month.JANUARY, 10), Restaurant.Sholly, "Чай", 50)
    );

    public static List<DishTo> getDishesTo(List<Dish> dishes, LocalDate date) {
        return dishes.stream()
                .filter(dish -> date.isEqual(dish.getDateOfLunch()))
                .map(dish -> new DishTo(dish.getDateOfLunch(), dish.getRestaurant(), dish.getNameOfDish(),  dish.getPrice()))
                .collect(Collectors.toList());
    }
}
