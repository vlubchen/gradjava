package ru.vlubchen.gradjava.web;

import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;
import ru.vlubchen.gradjava.model.User;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.vlubchen.gradjava.DishTestData.dishes;
import static ru.vlubchen.gradjava.UserTestData.*;
import static ru.vlubchen.gradjava.util.DishUtil.getDishesTo;

public class RootControllerTest extends AbstractControllerTest {
    @Test
    void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(3)))
                .andExpect(model().attribute("users",
                        new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(List<User> actual) throws AssertionError {
                                USER_MATCHER.assertMatch(actual, admin, guest, user);
                            }
                        }
                ));
    }

    @Test
    void getDishes() throws Exception {
        perform(get("/dishes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("dishes"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/dishes.jsp"))
                .andExpect(model().attribute("dishes", getDishesTo(dishes)));
    }
}