package ru.alfabank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/** Получаем от пользователя ключевое слово и передаём его в SQL-запрос */
public class DBServlet extends HttpServlet {

    private String hostname = "jdbc:postgresql://localhost:5432/testdb";
    private String login = "postgres";
    private String password = "admin";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Получаю параметр из графической формы
        String name = req.getParameter("username");

        try {
            // Загружаем и регистрируем драйвер
            Class.forName("org.postgresql.Driver");
            // Устанавливаем соединение с СУБД
            Connection con = DriverManager.getConnection(hostname, login, password);
            // Строим запрос
            PreparedStatement stmt = con.prepareStatement("select cust_contact, cust_email from customers where cust_name = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            // Разбираем ответ
            while (rs.next()) {
                String customer = rs.getString(1);
                String email = rs.getString(2);
                out.print("<br>" + "customer: " + customer + " &nbsp &nbsp &nbsp &nbsp email: " + email + "</br>");
            }
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.close();
    }
}
