package ru.vlubchen.gradjava.util;

import ru.vlubchen.gradjava.model.Restaurant;

import java.util.Arrays;
import java.util.List;

public class RestaurantUtil {

    public static final List<Restaurant> restaurants = Arrays.asList(
            new Restaurant("Sholly", "+7(8512)51-60-57", "ул. Урицкого, д.3", "upravl_sholi@am-house.ru"),
            new Restaurant("Izba", "+7(8512)51-81-91", "ул. Красная Набережная, д.8", "izba2012@inbox.ru"),
            new Restaurant("BeerHouse", "+7(8512)54-72-72", "ул. Савушкина, д.38", "pivdom@am-house.ru")
    );
}