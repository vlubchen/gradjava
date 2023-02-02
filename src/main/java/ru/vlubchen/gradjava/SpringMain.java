package ru.vlubchen.gradjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.model.Restaurant;
import ru.vlubchen.gradjava.model.Role;
import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.repository.DishRepository;
import ru.vlubchen.gradjava.repository.UserRepository;
import ru.vlubchen.gradjava.web.dish.DishRestController;
import ru.vlubchen.gradjava.web.user.AdminRestController;
import ru.vlubchen.gradjava.web.user.ProfileRestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names:" + Arrays.toString(appCtx.getBeanDefinitionNames()));
        //UserRepository userRepository = (UserRepository) appCtx.getBean("inMemoryUserRepository");
        UserRepository userRepository = appCtx.getBean(UserRepository.class);
        userRepository.getAll();

        AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
        adminRestController.create(new User(null, "adminName", "admin@mail.ru", "admin",
                true, new Date(), Role.ADMIN));
        ProfileRestController profileRestController = appCtx.getBean(ProfileRestController.class);
        profileRestController.create(new User(null, "userName", "user@mail.ru", "user",
                true, new Date(), Role.USER));

        DishRepository dishRepository = appCtx.getBean(DishRepository.class);
        DishRestController dishRestController = appCtx.getBean(DishRestController.class);
        dishRestController.create(new Dish(null, LocalDate.of(2023, Month.JANUARY, 9), Restaurant.BeerHouse,"Новое блюдо",250));
        appCtx.close();
    }
}