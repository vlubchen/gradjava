package ru.vlubchen.gradjava;

import ru.vlubchen.gradjava.model.Dish;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vlubchen.gradjava.model.AbstractBaseEntity.START_SEQ;
import static ru.vlubchen.gradjava.util.RestaurantUtil.restaurants;

public class DishTestData {
    public static final int NOT_FOUND = 10;
    public static final int DISH1_ID = START_SEQ + 6;

    public static final Dish dish1 = new Dish(DISH1_ID, LocalDate.of(2023, Month.JANUARY, 9), restaurants.get(0), "Грибной суп", 250);
    public static final Dish dish2 = new Dish(DISH1_ID + 4, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(0), "Стейк", 800);
    public static final Dish dish3 = new Dish(DISH1_ID + 5, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(0), "Винегрет", 150);
    public static final Dish dish4 = new Dish(DISH1_ID + 6, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(0), "Компот", 50);
    public static final Dish dish5 = new Dish(DISH1_ID + 7, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(1), "Борщ", 400);
    public static final Dish dish6 = new Dish(DISH1_ID + 8, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(1), "Плов", 500);
    public static final Dish dish7 = new Dish(DISH1_ID + 9, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(1), "Оливье", 150);
    public static final Dish dish8 = new Dish(DISH1_ID + 10, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(1), "Чай", 50);
    public static final Dish dish9 = new Dish(DISH1_ID + 11, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(2), "Грибной суп", 250);
    public static final Dish dish10 = new Dish(DISH1_ID + 12, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(2), "Стейк", 800);
    public static final Dish dish11 = new Dish(DISH1_ID + 13, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(2), "Винегрет", 150);
    public static final Dish dish12 = new Dish(DISH1_ID + 14, LocalDate.of(2023, Month.MARCH, 9), restaurants.get(2), "Чай", 50);
    public static final Dish dish13 = new Dish(DISH1_ID + 15, LocalDate.of(2023, Month.MARCH, 10), restaurants.get(1), "Борщ", 400);
    public static final Dish dish14 = new Dish(DISH1_ID + 16, LocalDate.of(2023, Month.MARCH, 10), restaurants.get(1), "Плов", 500);
    public static final Dish dish15 = new Dish(DISH1_ID + 17, LocalDate.of(2023, Month.MARCH, 10), restaurants.get(1), "Оливье", 150);
    public static final Dish dish16 = new Dish(DISH1_ID + 18, LocalDate.of(2023, Month.MARCH, 10), restaurants.get(1), "Чай", 50);

    public static final List<Dish> dishes = Arrays.asList(dish1, dish2, dish3, dish3, dish5, dish6, dish7, dish8, dish9, dish10, dish11, dish12, dish13, dish14, dish15, dish16);

    public static Dish getNew() {
        return new Dish(null, LocalDate.of(2023, Month.JANUARY, 15), restaurants.get(0), "Новое блюдо", 500);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, dish1.getDay().plus(2, ChronoUnit.DAYS), dish1.getRestaurant(), "Обновленное блюдо", 200);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("restaurant").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("restaurant").isEqualTo(expected);
    }
}
