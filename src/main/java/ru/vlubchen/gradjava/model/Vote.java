package ru.vlubchen.gradjava.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vote extends AbstractNamedEntity {
    private LocalDate day;
    private LocalTime time;
    private User user;
    private Restaurant restaurant;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Vote(Integer id, String name, LocalDate day, LocalTime time, User user, Restaurant restaurant) {
        super(id, name);
        this.day = day;
        this.time = time;
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