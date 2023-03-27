package ru.vlubchen.gradjava.web.dish;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vlubchen.gradjava.model.Dish;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Objects;

import static ru.vlubchen.gradjava.util.DateTimeUtil.parseLocalDate;
import static ru.vlubchen.gradjava.util.RestaurantUtil.restaurants;

@Controller
@RequestMapping("/dishes")
public class JspDishController extends AbstractDishController {
    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/dishes";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("dish", super.get(getId(request)));
        return "dishForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("dish", new Dish(LocalDate.now(), restaurants.get(0), "Новое блюдо", 500));
        return "dishForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Dish dish = new Dish(LocalDate.parse(request.getParameter("day")),
                restaurantRepository.getByName(request.getParameter("restaurant")),
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("price")));

        if (request.getParameter("id").isEmpty()) {
            super.create(dish);
        } else {
            super.update(dish, getId(request));
        }
        return "redirect:/dishes";
    }

    @GetMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate day = parseLocalDate(request.getParameter("day"));
        model.addAttribute("dishes", day != null ? super.getByDay(day) : super.getAll());
        return "dishes";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
