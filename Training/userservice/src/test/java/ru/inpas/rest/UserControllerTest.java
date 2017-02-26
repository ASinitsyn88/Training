package ru.inpas.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ru.inpas.InpasServiceApplication;
import ru.inpas.dao.UserDao;
import ru.inpas.dto.User;
import ru.inpas.repository.UserRepository;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alex on 23.02.2017.
 * Unit-test for UserController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(InpasServiceApplication.class)
@WebIntegrationTest("server.port:0")
public class UserControllerTest {

    private String host = "http://localhost:";

    @Value("${local.server.port}")
    private Integer port;

    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplate = new TestRestTemplate();

    private User userTest;

    @Before
    public void setUp() throws Exception {

        User user = User.builder()
                .firstname("TestName")
                .lastname("TestLastname")
                .patronymic("TestPatronymic")
                .phone("79161505674")
                .login("Test_Login")
                .build();
        userTest = userRepository.save(new UserDao(user));
    }

    @After
    public void tearDown() throws Exception {

        if (userTest != null && userTest.getId() != null) {
            userRepository.delete(userTest.getId());
        }
    }

    @Test
    public void findOneById() throws Exception {

        long id = userTest.getId();
        HttpHeaders headers = new HttpHeaders();
        ParameterizedTypeReference<User> typeReference = new ParameterizedTypeReference<User>() {};
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users/byId/" + id, HttpMethod.GET, new HttpEntity(headers), typeReference);

        assertEquals(response.getStatusCode().value(), 200);
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), userTest);
    }

    @Test
    public void findOneByIdNotFound() throws Exception {

        long failId = -1L;
        HttpHeaders headers = new HttpHeaders();
        ParameterizedTypeReference<User> typeReference = new ParameterizedTypeReference<User>() {};
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users/byId/" + failId, HttpMethod.GET, new HttpEntity(headers), typeReference);

        assertEquals(response.getStatusCode().value(), 200);
        assertNull(response.getBody());
    }

    @Test
    public void findAll() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        ParameterizedTypeReference<List<User>> typeReference = new ParameterizedTypeReference<List<User>>() {};
        ResponseEntity<List<User>> response = restTemplate.exchange(host + port + "/users", HttpMethod.GET, new HttpEntity(headers), typeReference);

        assertEquals(response.getStatusCode().value(), 200);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().stream().filter(u -> u.getLogin().equals("Test_Login")).findFirst().get());
    }

    @Test
    public void findByLogin() throws Exception {

        String login = "Test_Login";
        HttpHeaders headers = new HttpHeaders();
        ParameterizedTypeReference<User> typeReference = new ParameterizedTypeReference<User>() {};
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users/byLogin/" + login, HttpMethod.GET, new HttpEntity(headers), typeReference);

        assertEquals(response.getStatusCode().value(), 200);
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), userTest);
    }

    @Test
    public void findByLoginNotFound() throws Exception {

        String login = "FailLogin";
        HttpHeaders headers = new HttpHeaders();
        ParameterizedTypeReference<User> typeReference = new ParameterizedTypeReference<User>() {};
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users/byLogin/" + login, HttpMethod.GET, new HttpEntity(headers), typeReference);

        assertEquals(response.getStatusCode().value(), 200);
        assertNull(response.getBody());
    }

    @Test
    public void create() throws Exception {

        User user = User.builder()
                .firstname("NameForCreate")
                .lastname("LastnameForCreate")
                .patronymic("PatronymicForCreate")
                .phone("79161505674")
                .login("LoginForCreate")
                .build();
        HttpEntity<User> httpEntity = new HttpEntity<User>(user);
        ParameterizedTypeReference<User> typeReference = new ParameterizedTypeReference<User>() {};
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users", HttpMethod.POST, httpEntity, typeReference);
        deleteTestData(response);
        assertEquals(response.getStatusCode().value(), 200);
        assertNotNull(response);
        assertEquals(response.getBody().getFirstname(), "NameForCreate");
    }

    @Test
    public void createNotNullIdFail() throws Exception {

        User user = User.builder()
                .id(-1L)
                .firstname("NameForCreate")
                .lastname("LastnameForCreate")
                .patronymic("PatronymicForCreate")
                .phone("79161505674")
                .login("LoginForCreate")
                .build();
        HttpEntity<User> httpEntity = new HttpEntity<User>(user);
        ParameterizedTypeReference<User> typeReference = new ParameterizedTypeReference<User>() {};
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users", HttpMethod.POST, httpEntity, typeReference);
        deleteTestData(response);
        assertEquals(response.getStatusCode().value(), 200);
        assertNull(response.getBody());
    }

    @Test
    public void update() throws Exception {

        String originFirstname = userTest.getFirstname();
        userTest.setFirstname("NameForUpdate");
        HttpEntity<User> httpEntity = new HttpEntity<User>(userTest);
        ParameterizedTypeReference<User> typeReference = new ParameterizedTypeReference<User>() {};
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users", HttpMethod.PUT, httpEntity, typeReference);

        userTest.setFirstname(originFirstname);
        userRepository.save(new UserDao(userTest));

        assertEquals(response.getStatusCode().value(), 200);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getFirstname(), "NameForUpdate");
    }

    @Test
    public void updateNullIdFail() throws Exception {

        Long originalId = userTest.getId();
        userTest.setId(null);
        HttpEntity<User> httpEntity = new HttpEntity<User>(userTest);
        ParameterizedTypeReference<User> typeReference = new ParameterizedTypeReference<User>() {};
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users", HttpMethod.PUT, httpEntity, typeReference);

        userTest.setId(originalId);
        userRepository.save(new UserDao(userTest));

        assertEquals(response.getStatusCode().value(), 200);
        assertNull(response.getBody());
    }

    @Test
    public void updateIdNotFoundFail() throws Exception {

        Long originalId = userTest.getId();
        userTest.setId(-1L);
        HttpEntity<User> httpEntity = new HttpEntity<User>(userTest);
        ParameterizedTypeReference<User> typeReference = new ParameterizedTypeReference<User>() {};
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users", HttpMethod.PUT, httpEntity, typeReference);

        userTest.setId(originalId);
        userRepository.save(new UserDao(userTest));

        assertEquals(response.getStatusCode().value(), 200);
        assertNull(response.getBody());
    }

    @Test
    public void delete() throws Exception {

        User user = User.builder()
                .firstname("NameForDelete")
                .lastname("LastnameForDelete")
                .patronymic("PatronymicForDelete")
                .phone("79161505674")
                .login("LoginForDelete")
                .build();
        User createdUser = userRepository.save(new UserDao(user));

        HttpEntity<User> httpEntity = new HttpEntity<User>(createdUser);
        ParameterizedTypeReference<Boolean> typeReference = new ParameterizedTypeReference<Boolean>() {};
        ResponseEntity<Boolean> response = restTemplate.exchange(host + port + "/users", HttpMethod.DELETE, httpEntity, typeReference);

        assertEquals(response.getStatusCode().value(), 200);
        assertNull(userRepository.findOneById(createdUser.getId()));
        assertTrue(response.getBody());
    }

    private void deleteTestData(ResponseEntity<User> response) {

        if (response != null && response.getBody() != null && response.getBody().getId() != null) {
            userRepository.delete(response.getBody().getId());
        }
    }
}
