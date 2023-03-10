package ru.vlubchen.gradjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcDishRepository implements DishRepository {

    private static final RowMapper<Dish> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Dish.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertDish;

    @Autowired
    public JdbcDishRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertDish = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("dish")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Dish save(Dish dish) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", dish.getId())
                .addValue("name", dish.getName())
                .addValue("day", dish.getDay())
                .addValue("price", dish.getPrice())
                .addValue("restaurantId", dish.getRestaurant().getId());

        if (dish.isNew()) {
            Number newId = insertDish.executeAndReturnKey(map);
            dish.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                    "UPDATE dish " +
                    "   SET name=:name, day=:day, price=:price, restaurant_id=:restaurantId" +
                    " WHERE id=:id", map) == 0) {
                return null;
            }
        }
        return dish;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM dish WHERE id=?", id) != 0;
    }

    @Override
    public Dish get(int id) {
        List<Dish> dishes = jdbcTemplate.query(
                "SELECT * FROM dish WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(dishes);
    }

    @Override
    public List<Dish> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM dish ORDER BY id", ROW_MAPPER);
    }

    @Override
    public List<Dish> getByDay(LocalDate day) {
        return jdbcTemplate.query(
                "SELECT * FROM dish WHERE day = ? ORDER BY id",
                ROW_MAPPER, day);
    }
}