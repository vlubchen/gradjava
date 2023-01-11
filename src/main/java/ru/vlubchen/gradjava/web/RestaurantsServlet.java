package ru.vlubchen.gradjava.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RestaurantsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
    }
}