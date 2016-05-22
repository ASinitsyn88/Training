package ru.alfabank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            // Считываем из запроса cookie,которые передал браузер
            Cookie cookies[] = req.getCookies();
            out.print("Hello " + cookies[0].getValue());

            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
