package ru.alfabank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Пример сохранения и получения данных из cookies */
public class Servlet1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            // Получаем ввод пользователя и кладём его в cookie
            String userName = req.getParameter("userName");
            out.print("Welcome " + userName);

            // Создаём объект cookie и прикладываем его к ответу
            Cookie cookie = new Cookie("uname", userName);
            resp.addCookie(cookie);

            // Выводим на экран форму перехода на servlet2
            out.print("<form action='servlet2'>");
            out.print("<input type='submit' value='go'>");
            out.print("</form>");

            out.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
