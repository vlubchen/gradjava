package ru.vlubchen.gradjava.web.json;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vlubchen.gradjava.model.Dish;

import java.util.List;

import static ru.vlubchen.gradjava.DishTestData.*;

class JsonUtilTest {

    private static final Logger log = LoggerFactory.getLogger(JsonUtilTest.class);

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(dish1);
        log.info(json);
        Dish dish = JsonUtil.readValue(json, Dish.class);
        DISH_MATCHER.assertMatch(dish, dish1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(dishes);
        log.info(json);
        List<Dish> actual = JsonUtil.readValues(json, Dish.class);
        DISH_MATCHER.assertMatch(actual, dishes);
    }
}