package ru.alfabank.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    private String includeTo = "link.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        RequestDispatcher dispatcher = req.getRequestDispatcher(includeTo);
        dispatcher.include(req, resp);

        String login = req.getParameter("name");
        String password = req.getParameter("password");

        if (password.equals("admin123")) {
            out.print("Authorisation succesfull!");
            out.print("<br>Welcome, " + login);

            Cookie ck = new Cookie("login", login);
            resp.addCookie(ck);
        } else {
            out.print("Wrong login or password!");
            req.getRequestDispatcher("login.html").include(req, resp);
        }
        out.close();
    }
}
