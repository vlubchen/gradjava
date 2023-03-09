package ru.vlubchen.gradjava.util;

import ru.vlubchen.gradjava.model.Restaurant;

import java.util.Arrays;
import java.util.List;

public class RestaurantUtil {

    public static final List<Restaurant> restaurants = Arrays.asList(
            new Restaurant(100003,"Sholly", "+7(8512)51-60-57", "ул. Урицкого, д.3", "upravl_sholi@am-house.ru"),
            new Restaurant(100004, "Izba", "+7(8512)51-81-91", "ул. Красная Набережная, д.8", "izba2012@inbox.ru"),
            new Restaurant(100005,"BeerHouse", "+7(8512)54-72-72", "ул. Савушкина, д.38", "pivdom@am-house.ru")
    );
}