package ru.vlubchen.gradjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vlubchen.gradjava.model.Role;
import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.repository.UserRepository;
import ru.vlubchen.gradjava.web.user.AdminRestController;

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
        adminRestController.create(new User(null, "userName", "email@mail.ru", "password",
                true, new Date(), Role.ADMIN));
        appCtx.close();
    }
}