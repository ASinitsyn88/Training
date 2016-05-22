package ru.alfabank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            // Получаем данные из скрытого поля
            String name = req.getParameter("uname");
            out.print("Hello " + name);
            out.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
