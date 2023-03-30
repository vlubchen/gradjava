package ru.vlubchen.gradjava.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vlubchen.gradjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vlubchen.gradjava.UserTestData.ADMIN_ID;
import static ru.vlubchen.gradjava.UserTestData.admin;
import static ru.vlubchen.gradjava.web.json.JsonUtil.writeIgnoreProps;

class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                //.andExpect(content().json(JsonUtil.writeValue(admin)));
                .andExpect(content().json(writeIgnoreProps(admin, "registered")));
    }
}
