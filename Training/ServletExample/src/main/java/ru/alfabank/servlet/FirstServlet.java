package ru.alfabank.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("## FirstServlet.");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<html><body>");
        out.print("<br>FirstServlet started</br>");
        out.print("</body></html>");
    }
}
