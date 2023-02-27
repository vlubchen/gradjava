package ru.vlubchen.gradjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.DishRepository;
import ru.vlubchen.gradjava.util.DishUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryDishRepository implements DishRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryDishRepository.class);
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
        return filterByPredicate(dish -> true);
    }

    @Override
    public List<Dish> getByDay(LocalDate day) {
        return day != null ? filterByPredicate(dish -> day.isEqual(dish.getDay())) : filterByPredicate(dish -> true);
    }

    private List<Dish> filterByPredicate(Predicate<Dish> filter) {
        return dishes.values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Dish::getDay).reversed())
                .collect(Collectors.toList());
    }
}