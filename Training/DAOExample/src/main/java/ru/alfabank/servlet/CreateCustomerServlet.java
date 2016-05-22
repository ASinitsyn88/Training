package ru.alfabank.servlet;

import ru.alfabank.dao.CustomerDAO;
import ru.alfabank.dao_impl.CustomerDAOImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Сервлет для графической формы создания клиента */
public class CreateCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Получаем из графической формы id и создаём клиента
        String id = req.getParameter("id");
        CustomerDAO cusDao = new CustomerDAOImpl();
        int rowAdded = cusDao.insertCustomerById(id);
        out.print("<br>" + "row added: " + rowAdded + "</br>");
        out.close();
    }
}
