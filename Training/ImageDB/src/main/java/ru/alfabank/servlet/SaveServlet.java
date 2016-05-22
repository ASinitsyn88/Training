package ru.alfabank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/** Сохранение изображения в СУБД */
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            // Загружаем и регистрируем драйвер
            Class.forName("org.postgresql.Driver");
            // Устанавливаем соединение с СУБД
            Connection con = DriverManager.getConnection(Constants.HOSTNAME, Constants.LOGIN, Constants.PASSWORD);
            // Строим запрос
            PreparedStatement ps = con.prepareStatement("insert into imgtable values(?,?)");
            File file = new File("d:\\testPhoto.jpg");
            FileInputStream fin = new FileInputStream(file);
            ps.setString(1, file.getName());
            ps.setBinaryStream(2, fin, file.length());
            int i = ps.executeUpdate();

            out.print("<br>" + i + " records affected" + "</br>");

            ps.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.close();
    }
}
