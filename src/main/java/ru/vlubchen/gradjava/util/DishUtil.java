package ru.vlubchen.gradjava.util;

import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.model.Restaurant;
import ru.vlubchen.gradjava.to.DishTo;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DishUtil {
    public static void main(String[] args) {
        List<Dish> dishes = Arrays.asList(
                new Dish(LocalDate.of(2023, Month.JANUARY, 9), "Грибной суп", Restaurant.BeerHouse, 250),
                new Dish(LocalDate.of(2023, Month.JANUARY, 9), "Стейк", Restaurant.BeerHouse, 800),
                new Dish(LocalDate.of(2023, Month.JANUARY, 9), "Винегрет", Restaurant.BeerHouse, 150),
                new Dish(LocalDate.of(2023, Month.JANUARY, 9), "Компот", Restaurant.BeerHouse, 50),
                new Dish(LocalDate.of(2023, Month.JANUARY, 9), "Борщ", Restaurant.Sholly, 400),
                new Dish(LocalDate.of(2023, Month.JANUARY, 9), "Плов", Restaurant.Sholly, 500),
                new Dish(LocalDate.of(2023, Month.JANUARY, 9), "Оливье", Restaurant.Sholly, 150),
                new Dish(LocalDate.of(2023, Month.JANUARY, 9), "Чай", Restaurant.Sholly, 50),
                new Dish(LocalDate.of(2023, Month.JANUARY, 10), "Грибной суп", Restaurant.BeerHouse, 250),
                new Dish(LocalDate.of(2023, Month.JANUARY, 10), "Стейк", Restaurant.BeerHouse, 800),
                new Dish(LocalDate.of(2023, Month.JANUARY, 10), "Винегрет", Restaurant.BeerHouse, 150),
                new Dish(LocalDate.of(2023, Month.JANUARY, 10), "Компот", Restaurant.BeerHouse, 50),
                new Dish(LocalDate.of(2023, Month.JANUARY, 10), "Борщ", Restaurant.Sholly, 400),
                new Dish(LocalDate.of(2023, Month.JANUARY, 10), "Плов", Restaurant.Sholly, 500),
                new Dish(LocalDate.of(2023, Month.JANUARY, 10), "Оливье", Restaurant.Sholly, 150),
                new Dish(LocalDate.of(2023, Month.JANUARY, 10), "Чай", Restaurant.Sholly, 50)
        );

        //List<DishTo> dishesTo = filteredByCycles(dishes, LocalDate.of(2023, Month.JANUARY, 9), Restaurant.BeerHouse, 500);
        List<DishTo> dishesTo = filteredByStreams(dishes, LocalDate.of(2023, Month.JANUARY, 9), Restaurant.Sholly, 500);
        dishesTo.forEach(System.out::println);
    }

    public static List<DishTo> filteredByCycles(List<Dish> dishes, LocalDate date, Restaurant restaurant, int votes) {
        List<DishTo> filteredDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (date.isEqual(dish.getDate()) && restaurant == dish.getRestaurant()) {
                filteredDishes.add(new DishTo(dish.getDate(), dish.getName(), dish.getRestaurant(), dish.getPrice(), votes));
            }
        }
        return filteredDishes;
    }

    public static List<DishTo> filteredByStreams(List<Dish> dishes, LocalDate date, Restaurant restaurant, int votes) {
        return dishes.stream()
                .filter(dish -> date.isEqual(dish.getDate()))
                .filter(dish -> restaurant == dish.getRestaurant())
                .map(dish -> new DishTo(dish.getDate(), dish.getName(), dish.getRestaurant(), dish.getPrice(), votes))
                .collect(Collectors.toList());
    }
}
