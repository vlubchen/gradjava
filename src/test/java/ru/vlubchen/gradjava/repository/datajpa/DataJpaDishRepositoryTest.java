package ru.vlubchen.gradjava.repository.datajpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.AbstractRepositoryTest;
import ru.vlubchen.gradjava.repository.DishRepository;
import ru.vlubchen.gradjava.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.vlubchen.gradjava.DishTestData.*;
import static ru.vlubchen.gradjava.util.RestaurantUtil.restaurants;
import static ru.vlubchen.gradjava.util.ValidationUtil.checkNotFoundWithId;

public class DataJpaDishRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private DishRepository repository;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setup() {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void save() {
        Dish created = repository.save(getNew());
        Integer newId = created.getId();
        Dish newDish = getNew();
        newDish.setId(newId);
        assertMatch(created, newDish);
        assertMatch(repository.get(newId), newDish);
    }

    @Test
    public void delete() {
        repository.delete(DISH1_ID);
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(repository.get(DISH1_ID), DISH1_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(repository.delete(NOT_FOUND), NOT_FOUND));
    }

    @Test
    public void duplicateDayNameCreate() {
        assertThrows(DataAccessException.class, () ->
                repository.save(new Dish(null, dish1.getDay(), restaurants.get(0), dish1.getName(), 500)));
    }

    @Test
    public void get() {
        Dish dish = repository.get(DISH1_ID);
        assertMatch(dish, dish1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(repository.get(NOT_FOUND), NOT_FOUND));
    }

    @Test
    public void getAll() {
        List<Dish> all = repository.getAll();
        assertMatch(all, dishes);
    }

    @Test
    public void getByDay() {
        assertMatch(repository.getByDay(LocalDate.of(2023, Month.MARCH, 10)),
                dish16, dish15, dish14, dish13);
    }

    @Test
    public void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> repository.save(new Dish(null, null, restaurants.get(1), "Чай", 50)));
        validateRootCause(ConstraintViolationException.class, () -> repository.save(new Dish(null, LocalDate.of(2023, Month.MARCH, 10), restaurants.get(1), "Чай", 0)));
        validateRootCause(ConstraintViolationException.class, () -> repository.save(new Dish(null, LocalDate.of(2023, Month.MARCH, 10), restaurants.get(1), "Чай", 9)));
        validateRootCause(ConstraintViolationException.class, () -> repository.save(new Dish(null, LocalDate.of(2023, Month.MARCH, 10), restaurants.get(1), "Стэйк", 10001)));
    }
}