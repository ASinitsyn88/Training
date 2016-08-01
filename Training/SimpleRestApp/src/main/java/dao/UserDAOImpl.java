package dao;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Слой работы с БД
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String CREATE_USER = "INSERT INTO users(name, lastname, middlename, login, password, isActive) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET name=?, lastname=?, middlename=?, login=?, password=?, isActive=? WHERE id=?";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";

    private Connection con = null;

    // Конструктор
    public UserDAOImpl() {

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
            con = ds.getConnection();
        } catch (SQLException e) {
            System.out.println("Fault to connect to DB");
            e.printStackTrace();
        } catch (NamingException e) {
            System.out.println("Can not find tomcat datasource");
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(long id) {

        User user = null;
        logger.debug("## connect to the database ...");
        try (PreparedStatement stmt = con.prepareStatement(GET_USER_BY_ID)) {
            if (!stmt.isClosed()) {
                logger.debug("## database connection successfully");
            }
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setLastname(rs.getString("lastname"));
                    user.setMiddlename(rs.getString("middlename"));
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setActive(rs.getInt("isActive"));
                }
                logger.debug("## data obtain successfully");
            }
            logger.debug("## connection to the database is closed");
        } catch (SQLException e) {
            logger.error("Fault connection to database", e);
        }
        return user;
    }

    @Override
    public long createUser(User user) {

        long id = 0;
        System.out.println("## connect to the database ...");
        try (PreparedStatement stmt = con.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            if (!stmt.isClosed()) {
                logger.debug("## database connection successfully");
            }
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getMiddlename());
            stmt.setString(4, user.getLogin());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.isActive());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                while (rs.next()) {
                    id = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Fault connection to database", e);
        }
        return id;
    }

    @Override
    public void updateUser(User user) {

        logger.debug("## connect to the database ...");
        try (PreparedStatement stmt = con.prepareStatement(UPDATE_USER)) {
            if (!stmt.isClosed()) {
                logger.debug("## database connection successfully");
            }
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getMiddlename());
            stmt.setString(4, user.getLogin());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.isActive());
            stmt.setLong(7, user.getId());
            int rowAffected = stmt.executeUpdate();
            logger.debug("## Update successfully. Row affected: " + rowAffected);
        } catch (SQLException e) {
            logger.error("Fault connection to database", e);
        }
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {

        User user = null;
        logger.debug("## connect to the database ...");
        try (PreparedStatement stmt = con.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            if (!stmt.isClosed()) {
                logger.debug("## database connection successfully");
            }
            stmt.setString(1, login);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setLastname(rs.getString("lastname"));
                    user.setMiddlename(rs.getString("middlename"));
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setActive(rs.getInt("isActive"));
                }
                logger.debug("## data obtain successfully");
            }
            logger.debug("## connection to the database is closed");
        } catch (SQLException e) {
            logger.error("Fault connection to database", e);
        }
        return user;
    }
}
