package ru.alfabank.servlet;

import ru.alfabank.dao.CustomerDAO;
import ru.alfabank.dao_impl.CustomerDAOImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Сервлет для графической формы удаления клиента */
public class DeleteCustomerServlet extends HttpServlet {

    /** Метод delete не поддерживается html формой */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Получаем из графической формы id и изменяем клиента
        String id = req.getParameter("id");
        CustomerDAO cusDao = new CustomerDAOImpl();
        int rowDeleted = cusDao.deleteCustomerById(id);
        out.print("<br>" + "row deleted: " + rowDeleted + "</br>");
        out.close();
    }
}
