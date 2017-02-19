package ru.inpas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.inpas.dto.User;
import ru.inpas.repository.UserRepository;

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

    @Test
    public void findOneById() throws Exception {

        User user = userRepository.findOneById(1);
        System.out.println(user);
    }
}
