package ru.vlubchen.gradjava.repository;

import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.util.DishUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDishRepository implements DishRepository {

    private final Map<Integer, Dish> repository = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    {
        DishUtil.dishes.forEach(this::save);
    }

    @Override
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            dish.setId(counter.incrementAndGet());
            repository.put(dish.getId(), dish);
            return dish;
        }
        return repository.computeIfPresent(dish.getId(), (id, oldDish) -> dish);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Dish get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Dish> getAll() {
        return repository.values();
    }
}