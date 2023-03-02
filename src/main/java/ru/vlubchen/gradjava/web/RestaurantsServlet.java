package ru.vlubchen.gradjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vlubchen.gradjava.model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.vlubchen.gradjava.util.RestaurantUtil.restaurants;

public class RestaurantsServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(RestaurantsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("forward to restaurants");
        request.setAttribute("restaurants", restaurants.toArray());
        request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
    }
}