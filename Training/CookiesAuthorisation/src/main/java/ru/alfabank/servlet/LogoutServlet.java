package ru.alfabank.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {

    private String includeTo = "link.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        RequestDispatcher dispatcher = req.getRequestDispatcher(includeTo);
        dispatcher.include(req, resp);

        Cookie ck = new Cookie("name", "");
        ck.setMaxAge(0);
        resp.addCookie(ck);

        out.print("you are successfully logged out!");
    }
}
