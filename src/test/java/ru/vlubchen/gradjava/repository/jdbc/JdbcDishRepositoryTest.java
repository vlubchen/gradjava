package ru.vlubchen.gradjava.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.DishRepository;
import ru.vlubchen.gradjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.vlubchen.gradjava.DishTestData.*;
import static ru.vlubchen.gradjava.util.RestaurantUtil.restaurants;
import static ru.vlubchen.gradjava.util.ValidationUtil.checkNotFoundWithId;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcDishRepositoryTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private DishRepository repository;

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
                dish13, dish14, dish15, dish16);
    }
}