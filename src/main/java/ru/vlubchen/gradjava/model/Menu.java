package ru.vlubchen.gradjava.model;

public class Menu extends AbstractNamedEntity {
    private Restaurant restaurant;
    private CategoryMenu categoryMenu;
    private int price;

    public Menu(Integer id, String name, Restaurant restaurant, CategoryMenu categoryMenu, int price) {
        super(id, name);
        this.restaurant = restaurant;
        this.categoryMenu = categoryMenu;
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public CategoryMenu getCategoryMenu() {
        return categoryMenu;
    }

    public void setCategoryMenu(CategoryMenu categoryMenu) {
        this.categoryMenu = categoryMenu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}