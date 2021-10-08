package ru.sinitsyn.mypostgresrest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.google.gson.JsonParser.parseString;

@RestController
public class Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private final Connection con;

    @Autowired
    public Controller(@Value("${db.url}") String url, @Value("${db.login}") String login, @Value("${db.password}") String password) {
        try {
            con = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred during establishing DB connection via url:" + url + " login:" + login + " password:" + password);
        }
    }

    private JsonElement executeQuery(String sql) {
        try (Statement stmt = con.createStatement()) {
            return stmt.execute(sql)
                    ? parseString(new Gson().toJson(getResult(stmt), new TypeToken<List<Person>>() {}.getType()))
                    : new JsonObject();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred during execute sql " + sql + " " + e);
        }
    }

    private static List<Person> getResult(Statement stmt) throws SQLException {
        List<Person> personList = new ArrayList<>();
        try (ResultSet rs = stmt.getResultSet()) {
            while (rs != null && rs.next()) {
                Integer id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                personList.add(new Person(id, firstName, lastName));
            }
        }
        return personList;
    }

    @RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.OK).body("{\"status\":\"OK\"}");
    }

    @RequestMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> invoke() {
        JsonElement dbData = executeQuery("SELECT id, firstName, lastName FROM public.persons");
        return ResponseEntity.status(HttpStatus.OK).body(dbData.toString());
    }
}