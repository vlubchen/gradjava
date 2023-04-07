package ru.vlubchen.gradjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vlubchen.gradjava.repository.DishRepository;
import ru.vlubchen.gradjava.util.DishUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "index";
    }

    @GetMapping("/users")
    public String getUsers() {
        log.info("users");
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("setUser {}", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:dishes";
    }

    @GetMapping("/dishes")
    public String getDishes(Model model) {
        log.info("dishes");
        model.addAttribute("dishes",
                DishUtil.getDishesTo(dishRepository.getAll()));
        return "dishes";
    }
}

