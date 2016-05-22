package ru.alfabank.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    /** Контекст для хранения счётчиков */
    public ServletContext ctx = null;
    /** Общее кол-во пользователей*/
    public static int total = 0;
    /** Кол-во текущих пользователей */
    public static int current = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        System.out.print("sessionCreated work!");
        total++;
        current++;
        ctx = se.getSession().getServletContext();
        ctx.setAttribute("totalusers", total);
        ctx.setAttribute("currentusers", current);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        System.out.print("sessionDestroyed work!");
        current--;
        ctx.setAttribute("currentusers",current);
    }
}
