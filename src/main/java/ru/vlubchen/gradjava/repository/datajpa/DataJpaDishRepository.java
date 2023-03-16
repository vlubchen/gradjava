package ru.vlubchen.gradjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {

    private static final Sort SORT_ID = Sort.by(Sort.Direction.DESC, "id");
    private final CrudDishRepository crudRepository;

    public DataJpaDishRepository(CrudDishRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Dish save(Dish dish) {
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll() {
        return crudRepository.findAll(SORT_ID);
    }

    @Override
    public List<Dish> getByDay(LocalDate day) {
        return crudRepository.getByDay(day);
    }
}