package ru.vlubchen.gradjava.model;

import java.time.LocalDate;

public class Vote extends AbstractNamedEntity {
    private LocalDate day;
    private User user;
    private Restaurant restaurant;

    public Vote(Integer id, String name, LocalDate day, User user, Restaurant restaurant) {
        super(id, name);
        this.day = day;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}