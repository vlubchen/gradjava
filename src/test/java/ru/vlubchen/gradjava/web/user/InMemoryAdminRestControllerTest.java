package ru.vlubchen.gradjava.web.user;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vlubchen.gradjava.repository.inmemory.InMemoryUserRepository;
import ru.vlubchen.gradjava.util.exception.NotFoundException;

import java.util.Arrays;

import static ru.vlubchen.gradjava.UserTestData.NOT_FOUND;
import static ru.vlubchen.gradjava.UserTestData.USER_ID;

public class InMemoryAdminRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(InMemoryAdminRestControllerTest.class);

    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;
    private static InMemoryUserRepository repository;


    @BeforeAll
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/inmemory.xml");
        log.info("\n{}\n", Arrays.toString(appCtx.getBeanDefinitionNames()));
        controller = appCtx.getBean(AdminRestController.class);
        repository = appCtx.getBean(InMemoryUserRepository.class);
    }

    @AfterAll
    public static void afterClass() {
        appCtx.close();
    }

    @BeforeEach
    public void setUp() {
        // re-initialize
        repository.init();
    }

    @Test
    public void delete() {
        controller.delete(USER_ID);
        Assertions.assertNull(repository.get(USER_ID));
    }

    @Test
    public void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}