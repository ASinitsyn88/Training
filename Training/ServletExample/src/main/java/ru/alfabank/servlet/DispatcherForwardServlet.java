package ru.alfabank.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Пример переброса из одного сервлета в другой при помощи forward */
public class DispatcherForwardServlet extends HttpServlet {

    String forwardTo = "FirstServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("## DispatcherForwardServlet. Forward to " + forwardTo);
        // Указываем url-pattern сервлета на который сделать forward
        RequestDispatcher rd = req.getRequestDispatcher(forwardTo);
        rd.forward(req, resp);
    }
}
