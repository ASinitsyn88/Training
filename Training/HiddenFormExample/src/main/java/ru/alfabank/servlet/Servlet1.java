package ru.alfabank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/** Пример использования скрытых полей */
public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            String name = req.getParameter("userName");
            HttpSession httpSession = req.getSession();
            String sessionId = httpSession.getId();
            out.print("Welcome " + name);
            out.print("<br>");
            out.print("sessionId " + sessionId);

            // Создаём форму со скрытым текстовым полем
            out.print("<form action='Servlet2'>");
            out.print("<input type='hidden' name='uname' value='"+ name +"'>");
            out.print("<input type='submit' value='go'>");
            out.print("</form>");
            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
