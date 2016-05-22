package ru.alfabank.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Пример получения информации из другого сервлета */
public class Servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        try {
            resp.setContentType("text/html");

            // Получаем данные из контекста приложения
            ServletContext context = getServletContext();
            String n = (String)context.getAttribute("Servlet1");

            out.println("Welcome to " + n);
            out.close();
        } catch (Exception e) {
            out.println(e);
        }
    }
}
