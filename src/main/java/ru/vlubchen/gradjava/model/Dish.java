package ru.vlubchen.gradjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"day", "restaurant_id", "name"}, name = "dish_unique_day_restaurant_id_name")})
public class Dish extends AbstractNamedEntity {

    @Column(name = "day", nullable = false)
    @NotNull
    private LocalDate day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 10000)
    @NotNull
    private int price;

    public Dish() {
    }
    public Dish(LocalDate day, Restaurant restaurant, String name, int price) {
        this(null, day, restaurant, name, price);
    }

    public Dish(Integer id, LocalDate day, Restaurant restaurant, String name, int price) {
        super(id, name);
        this.day = day;
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
    }

    public LocalDate getDay() {
        return day;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getPrice() {
        return price;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                ", id=" + id +
                "day=" + day +
                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}