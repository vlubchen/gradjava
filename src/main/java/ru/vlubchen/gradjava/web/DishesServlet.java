package ru.vlubchen.gradjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.model.Restaurant;
import ru.vlubchen.gradjava.repository.DishRepository;
import ru.vlubchen.gradjava.repository.inmemory.InMemoryDishRepository;
import ru.vlubchen.gradjava.util.DishUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class DishesServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DishesServlet.class);

    private DishRepository repository;

    @Override
    public void init() {
        repository = new InMemoryDishRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Dish dish = new Dish(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDate.parse(request.getParameter("day")),
                Restaurant.valueOf(request.getParameter("restaurant")),
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("price")));

        log.info(dish.isNew() ? "Create {}" : "Update {}", dish);
        repository.save(dish);
        response.sendRedirect("dishes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete id={}", id);
                repository.delete(id);
                response.sendRedirect("dishes");
                break;
            case "create":
            case "update":
                final Dish dish = "create".equals(action) ?
                        new Dish(LocalDate.now(), Restaurant.BeerHouse, "Новое блюдо", 0) :
                        repository.get(getId(request));
                request.setAttribute("dish", dish);
                request.getRequestDispatcher("/dishForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("dishes",
                        DishUtil.getDishesTo(repository.getAll()));
                request.getRequestDispatcher("/dishes.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}