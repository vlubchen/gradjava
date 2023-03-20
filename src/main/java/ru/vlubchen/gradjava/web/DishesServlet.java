package ru.vlubchen.gradjava.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.util.DateTimeUtil;
import ru.vlubchen.gradjava.web.dish.DishRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import static ru.vlubchen.gradjava.util.RestaurantUtil.restaurants;

public class DishesServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private DishRestController dishController;

    @Override
    public void init() {
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        dishController = springContext.getBean(DishRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Dish dish = new Dish(
                LocalDate.parse(request.getParameter("day")),
                restaurants.get(2),//request.getParameter("restaurant"),
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("price")));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            dishController.update(dish, getId(request));
        } else {
            dishController.create(dish);
        }
        response.sendRedirect("dishes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                dishController.delete(id);
                response.sendRedirect("dishes");
                break;
            case "create":
            case "update":
                final Dish dish = "create".equals(action) ?
                        new Dish(LocalDate.now(), restaurants.get(2), "Новое блюдо", 100) :
                        dishController.get(getId(request));
                request.setAttribute("dish", dish);
                request.getRequestDispatcher("/dishForm.jsp").forward(request, response);
                break;
            case "filter":
                LocalDate day = DateTimeUtil.parseLocalDate(request.getParameter("day"));
                request.setAttribute("dishes", dishController.getByDay(day));
                request.getRequestDispatcher("/dishes.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("dishes", dishController.getAll());
                request.getRequestDispatcher("/dishes.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}