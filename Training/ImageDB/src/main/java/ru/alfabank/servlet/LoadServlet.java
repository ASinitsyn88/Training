package ru.alfabank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/** Загрузка изображения из СУБД */
public class LoadServlet extends HttpServlet {

    private String name = "testPhoto.jpg";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Загружаем и регистрируем драйвер
            Class.forName("org.postgresql.Driver");
            // Устанавливаем соединение с СУБД
            Connection con = DriverManager.getConnection(Constants.HOSTNAME, Constants.LOGIN, Constants.PASSWORD);
            // Строим запрос
            PreparedStatement ps = con.prepareStatement("select * from imgtable where name = ?");
            ps.setString(1, name);
            // Разбираем ответ
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                byte[] content = rs.getBytes(2);
                resp.setContentType(getServletContext().getMimeType(name));
                resp.setContentLength(content.length);
                resp.getOutputStream().write(content);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
