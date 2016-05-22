package ru.alfabank.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Пример переброса из одного сервлета в другой при помощи include */
public class DispatcherIncludeServlet extends HttpServlet {

    String includeTo = "FirstServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("## DispatcherIncludeServlet started");
        System.out.println("## DispatcherIncludeServlet includeTo " + includeTo);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<html><body>");
        out.println("<br>DispatcherIncludeServlet started</br>");
        out.print("</body></html>");
        // Указываем url-pattern сервлета на который сделать include
        RequestDispatcher rd = req.getRequestDispatcher(includeTo);
        rd.include(req, resp);
        out.close();
    }
}
