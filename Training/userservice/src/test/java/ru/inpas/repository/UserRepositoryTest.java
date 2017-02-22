package ru.inpas.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.inpas.InpasServiceApplication;
import ru.inpas.dao.UserDao;
import ru.inpas.dto.User;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Alex on 19.02.2017.
 * Unit-test for UserRepository
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(InpasServiceApplication.class)
@WebIntegrationTest("server.port:0")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

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

        User response = userRepository.findOneById(userTest.getId());
        assertNotNull(response);
        assertEquals(response, userTest);
    }

    @Test
    public void findOneByIdNotFound() throws Exception {

        long failId = -1L;
        User response = userRepository.findOneById(failId);
        assertNull(response);
    }

    @Test
    public void findAll() throws Exception {

        Collection<UserDao> response = userRepository.findAll();
        assertNotNull(response);
        assertNotNull(response.stream().filter(u -> u.getLogin().equals("Test_Login")).findFirst().get());
    }

    @Test
    public void findByLogin() throws Exception {

        User response = userRepository.findByLogin("Test_Login");
        assertNotNull(response);
        assertEquals(response, userTest);
    }

    @Test
    public void findByLoginNotFound() throws Exception {

        String failLogin = "FailLogin";
        User response = userRepository.findByLogin(failLogin);
        assertNull(response);
    }
}
