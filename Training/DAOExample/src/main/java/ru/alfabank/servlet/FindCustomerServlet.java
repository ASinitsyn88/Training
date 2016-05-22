package ru.alfabank.servlet;

import ru.alfabank.dao.CustomerDAO;
import ru.alfabank.dao_impl.CustomerDAOImpl;
import ru.alfabank.pojo.Customer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Сервлет для графической формы поиска клиента */
public class FindCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Получаем из графической формы id и ищем клиента
        String id = req.getParameter("id");
        CustomerDAO cusDao = new CustomerDAOImpl();
        Customer cus = cusDao.getCustomerById(id);
        out.print("<br>" + "id: " + cus.getId() + "</br>");
        out.print("<br>" + "name: " + cus.getName() + "</br>");
        out.print("<br>" + "address: " + cus.getAddress() + "</br>");
        out.print("<br>" + "city: " + cus.getCity() + "</br>");
        out.print("<br>" + "email: " + cus.getEmail() + "</br>");
        out.close();
    }
}
