package ru.vlubchen.gradjava.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.vlubchen.gradjava.util.ValidationUtil.*;

@Controller
public class DishRestController {
    private final DishRepository repository;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public DishRestController(DishRepository repository) {
        this.repository = repository;
    }

    public List<Dish> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public Dish get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Dish create(Dish dish) {
        log.info("create {}", dish);
        checkNew(dish);
        return repository.save(dish);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(Dish dish, int id) {
        log.info("update {} with id={}", dish, id);
        assureIdConsistent(dish, id);
        checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    public List<Dish> getByDay(LocalDate day) {
        log.info("getByEmail {}", day);
        return checkNotFound(repository.getByDay(day), "day=" + day);
    }
}