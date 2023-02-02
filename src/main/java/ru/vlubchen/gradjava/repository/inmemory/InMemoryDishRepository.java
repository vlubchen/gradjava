package ru.vlubchen.gradjava.repository.inmemory;

import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.DishRepository;
import ru.vlubchen.gradjava.util.DishUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryDishRepository implements DishRepository {

    private final Map<Integer, Dish> dishes = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    {
        DishUtil.dishes.forEach(this::save);
    }

    @Override
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            dish.setId(counter.incrementAndGet());
            dishes.put(dish.getId(), dish);
            return dish;
        }
        return dishes.computeIfPresent(dish.getId(), (id, oldDish) -> dish);
    }

    @Override
    public boolean delete(int id) {
        return dishes.remove(id) != null;
    }

    @Override
    public Dish get(int id) {
        return dishes.get(id);
    }

    @Override
    public List<Dish> getAll() {
        return dishes.values().stream()
                .sorted(Comparator.comparing(Dish::getDay).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Dish> getByDay(LocalDate day) {
        return dishes.values().stream()
                .filter(dish -> day.isEqual(dish.getDay()))
                .sorted(Comparator.comparing(Dish::getDay).reversed())
                .collect(Collectors.toList());
    }
}