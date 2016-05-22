package ru.alfabank.dao_impl;

import ru.alfabank.dao.CustomerDAO;
import ru.alfabank.mock.Mock;
import ru.alfabank.pojo.Customer;
import ru.alfabank.util.Constants;
import java.sql.*;

/** Реализация CustomerDAO */
public class CustomerDAOImpl implements CustomerDAO {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can not load driver class: org.postgresql.Driver");
            e.printStackTrace();
        }
    }

    /** Получить объект соединения с СУБД */
    private Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(Constants.POSTGRESQL_HOSTNAME, Constants.POSTGRESQL_LOGIN, Constants.POSTGRESQL_PASSWORD);
        return connection;
    }

    /** Получить клиента по id */
    @Override
    public Customer getCustomerById(String id) {

        Customer cus = new Customer();
        try {
            // Получаю объект соедиения с СУБД
            Connection con = getConnection();
            // Подготавливаю запрос
            PreparedStatement ps = con.prepareStatement("select cust_id, cust_name, cust_address, cust_city, cust_email from customers where cust_id = ?");
            ps.setString(1, id);
            // Разбираю ответ
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cus.setId(rs.getString("cust_id"));
                cus.setName(rs.getString("cust_name"));
                cus.setAddress(rs.getString("cust_address"));
                cus.setCity(rs.getString("cust_city"));
                cus.setEmail(rs.getString("cust_email"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cus;
    }

    @Override
    public int insertCustomerById(String id) {

        Connection con = null;
        int rowAdded = 0;
        Customer cus = Mock.generateCustomerMock(id);
        try {
            // Получаю объект соедиения с СУБД
            con = getConnection();
            con.setAutoCommit(false);
            // Подготавливаю запрос
            PreparedStatement ps = con.prepareStatement("insert into customers values (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, cus.getId());
            ps.setString(2, cus.getName());
            ps.setString(3, cus.getAddress());
            ps.setString(4, cus.getCity());
            ps.setString(5, "");
            ps.setString(6, "");
            ps.setString(7, "");
            ps.setString(8, "");
            ps.setString(9, cus.getEmail());
            // Разбираю ответ
            rowAdded = ps.executeUpdate();
            con.commit();

            ps.close();
            con.close();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return rowAdded;
    }

    @Override
    public int updateCustomerById(String id) {

        Connection con = null;
        int rowUpdated = 0;
        try {
            // Получаю объект соедиения с СУБД
            con = getConnection();
            con.setAutoCommit(false);
            // Подготавливаю запрос
            PreparedStatement ps = con.prepareStatement("update customers set cust_name = 'UPD_TEST_NAME' where cust_id = ?");
            ps.setString(1, id);
            // Разбираю ответ
            rowUpdated = ps.executeUpdate();
            con.commit();

            ps.close();
            con.close();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public int deleteCustomerById(String id) {

        Connection con = null;
        int rowDeleted = 0;
        try {
            // Получаю объект соедиения с СУБД
            con = getConnection();
            con.setAutoCommit(false);
            // Подготавливаю запрос
            PreparedStatement ps = con.prepareStatement("delete from customers where cust_id = ?");
            ps.setString(1, id);
            // Разбираю ответ
            rowDeleted = ps.executeUpdate();
            con.commit();

            ps.close();
            con.close();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
