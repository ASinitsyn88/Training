package ru.alfabank.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Показывает настройки,которые передаются для инициализации сервлета */
public class ServletConfigExample extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        ServletConfig config = getServletConfig();
        ServletContext ctxConfig = getServletContext();
        String driver = config.getInitParameter("driver");
        String ctxDriver = ctxConfig.getInitParameter("dname");
        out.print("Driver is: " + driver);
        out.print("ctxDriver is: " + ctxDriver);
        out.close();
    }
}
