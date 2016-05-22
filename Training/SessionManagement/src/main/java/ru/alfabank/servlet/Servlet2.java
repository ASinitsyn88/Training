package ru.alfabank.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Получаем из сессии имя и выводим данные на экран
            HttpSession session = req.getSession(false);
            String name = (String)session.getAttribute("uname");
            String sessionId = session.getId();
            long ct = session.getCreationTime();
            long lat = session.getLastAccessedTime();
            int interval = session.getMaxInactiveInterval();

            // Преобразуем creationTime
            Date ctDate = new Date(ct);
            Calendar ctCal = new GregorianCalendar();
            ctCal.setTime(ctDate);
            int ctSeconds = ctCal.get(Calendar.SECOND);
            int ctMinute = ctCal.get(Calendar.MINUTE);
            int ctHour = ctCal.get(Calendar.HOUR_OF_DAY);
            int ctDay = ctCal.get(Calendar.DATE);
            int ctMonth = ctCal.get(Calendar.MONTH) + 1;
            int ctYear = ctCal.get(Calendar.YEAR);

            // Преобразуем lastAccessedTime
            Date latDate = new Date(lat);
            Calendar latCal = new GregorianCalendar();
            latCal.setTime(latDate);
            int latSeconds = ctCal.get(Calendar.SECOND);
            int latMinute = ctCal.get(Calendar.MINUTE);
            int latHour = ctCal.get(Calendar.HOUR_OF_DAY);
            int latDay = ctCal.get(Calendar.DATE);
            int latMonth = ctCal.get(Calendar.MONTH) + 1;
            int latYear = ctCal.get(Calendar.YEAR);

            ServletContext ctx=getServletContext();
            int totalUsers = (Integer)ctx.getAttribute("totalusers");
            int currentUsers = (Integer)ctx.getAttribute("currentusers");

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.print("Hello " + name);
            out.print("<br>");
            out.print("sessionId: " + sessionId);
            out.print("<br>");
            out.print("CreationTime: " + ctYear + ":" + ctMonth + ":" + ctDay + " " + ctHour + ":" + ctMinute + ":" + ctSeconds);
            out.print("<br>");
            out.print("LastAccessedTime: " + latYear + ":" + latMonth + ":" + latDay + " " + latHour + ":" + latMinute + ":" + latSeconds);
            out.print("<br>");
            out.print("MaxInactiveInterval: " + String.valueOf(interval));
            out.print("<br>");
            out.print("total users: " + String.valueOf(totalUsers));
            out.print("<br>");
            out.print("current users: " + String.valueOf(currentUsers));
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
