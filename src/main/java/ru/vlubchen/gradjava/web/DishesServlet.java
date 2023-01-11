package ru.vlubchen.gradjava.web;

import ru.vlubchen.gradjava.util.DishUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class DishesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dishes", DishUtil.getDishesTo(DishUtil.dishes, LocalDate.of(2023, 1,9)));
        request.getRequestDispatcher("/dishes.jsp").forward(request, response);
    }
}