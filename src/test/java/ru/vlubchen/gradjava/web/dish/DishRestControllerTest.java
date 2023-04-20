package ru.vlubchen.gradjava.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.DishRepository;
import ru.vlubchen.gradjava.util.exception.NotFoundException;
import ru.vlubchen.gradjava.web.AbstractControllerTest;
import ru.vlubchen.gradjava.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vlubchen.gradjava.DishTestData.*;
import static ru.vlubchen.gradjava.util.ValidationUtil.checkNotFoundWithId;

class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishRestController.REST_URL + '/';

    @Autowired
    DishRepository dishRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DISH1_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(dishRepository.get(DISH1_ID), DISH1_ID));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dishes));
    }

    @Test
    void update() throws Exception {
        Dish updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + DISH1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        DISH_MATCHER.assertMatch(dishRepository.get(DISH1_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Dish newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andExpect(status().isCreated());
        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishRepository.get(newId), newDish);
    }

    @Test
    void getByDay() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "filter?day=2023-03-10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(DISH_MATCHER.contentJson(dish16, dish15, dish14, dish13));
    }
}