package ru.vlubchen.gradjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RestaurantsServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(RestaurantsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("forward to restaurants");
        request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
    }
}