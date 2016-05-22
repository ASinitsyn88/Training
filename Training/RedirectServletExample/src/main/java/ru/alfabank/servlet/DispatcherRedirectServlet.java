package ru.alfabank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Redirect(работает на стороне клиента)
 * 1.Отправляем запрос на сервер
 * 2.Сервер отвечает ссылкой
 * 3.Браузер переходит по этой ссылке
 * */
public class DispatcherRedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        resp.sendRedirect("http://www.google.com");
        out.close();
    }
}
