package ru.inpas.service;

import org.junit.After;
import org.junit.Assert;
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
import ru.inpas.repository.UserRepository;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Alex on 22.02.2017.
 * Unit-test for UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(InpasServiceApplication.class)
@WebIntegrationTest("server.port:0")
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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

        User response = userService.findOneById(userTest.getId());
        assertNotNull(response);
        assertEquals(response, userTest);
    }

    @Test
    public void findOneByIdNotFound() throws Exception {

        long failId = -1L;
        User response = userService.findOneById(failId);
        Assert.assertNull(response);
    }

    @Test
    public void findAll() throws Exception {

        Collection<User> response = userService.findAll();
        assertNotNull(response);
        assertNotNull(response.stream().filter(u -> u.getLogin().equals("Test_Login")).findFirst().get());
    }

    @Test
    public void findByLogin() throws Exception {

        User response = userService.findByLogin("Test_Login");
        assertNotNull(response);
        assertEquals(response, userTest);
    }

    @Test
    public void findByLoginNotFound() throws Exception {

        String failLogin = "FailLogin";
        User response = userService.findByLogin(failLogin);
        assertNull(response);
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
        User response = userService.create(user);
        deleteTestData(response);

        assertNotNull(response);
        assertNotNull(response.getId());
    }

    @Test
    public void createNullFail() throws Exception {

        User response = userService.create(null);
        assertNull(response);
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
        User response = userService.create(user);
        deleteTestData(response);

        assertNull(response);
    }

    @Test
    public void update() throws Exception {

        String originFirstname = userTest.getFirstname();
        userTest.setFirstname("NameForUpdate");
        User response = userService.update(userTest);

        userTest.setFirstname(originFirstname);
        userService.update(userTest);

        assertNotNull(response);
        assertEquals(response.getFirstname(), "NameForUpdate");
    }

    @Test
    public void updateNullFail() throws Exception {

        User response = userService.update(null);
        assertNull(response);
    }

    @Test
    public void updateNullIdFail() throws Exception {

        Long originalId = userTest.getId();
        userTest.setId(null);

        User response = userService.update(userTest);
        userTest.setId(originalId);
        userService.update(userTest);

        assertNull(response);
    }

    @Test
    public void updateIdNotFoundFail() throws Exception {

        Long originalId = userTest.getId();
        userTest.setId(-1L);

        User response = userService.update(userTest);
        userTest.setId(originalId);
        userService.update(userTest);
        assertNull(response);
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
        User createdUser = userService.create(user);

        boolean isDeleted = userService.delete(createdUser);
        assertNull(userRepository.findOneById(createdUser.getId()));
        assertTrue(isDeleted);
    }

    private void deleteTestData(User response) {

        if (response != null && response.getId() != null) {
            userRepository.delete(response.getId());
        }
    }
}
