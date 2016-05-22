package ru.alfabank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/** Пример управления сессией */
public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Получаю имя и сохраняю его в сессию
            String name = req.getParameter("userName");
            HttpSession session = req.getSession();
            session.setAttribute("uname", name);

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.print("Welcome " + name);
            out.print("<a href='Servlet2'>visit</a>");
            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
