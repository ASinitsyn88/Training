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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ru.inpas.InpasServiceApplication;
import ru.inpas.dao.UserDao;
import ru.inpas.dto.User;
import ru.inpas.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        ResponseEntity<User> response = restTemplate.exchange(host + port + "/users", HttpMethod.POST, httpEntity, typeReference, user);
        deleteTestData(response);
        assertEquals(response.getStatusCode().value(), 200);
        assertNotNull(response);
        assertEquals(response.getBody().getFirstname(), "NameForCreate");
    }

    private void deleteTestData(ResponseEntity<User> response) {

        if (response != null && response.getBody() != null && response.getBody().getId() != null) {
            userRepository.delete(response.getBody().getId());
        }
    }
}
