package ru.alfabank.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Пример передачи информации из одного сервлета в другой */
public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        try {
            resp.setContentType("text/html");

            // Сохраняем данные в контекст приложения
            ServletContext context = getServletContext();
            context.setAttribute("Servlet1", "Info from Servlet1");

            out.println("Welcome to first servlet");
            out.println("<a href='servlet2'>visit</a>");
            out.close();

        } catch (Exception e) {
            out.println(e);
        }
    }
}
