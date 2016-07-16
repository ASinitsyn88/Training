package service;

import api.UserService;
import mock.UserMock;
import model.User;
import org.springframework.stereotype.Service;

/**
 * Реализация бизнес-логики
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(long id) {

        User user = UserMock.generateUser();
        if (user.getId() != id) {
            return null;
        }
        return user;
    }

    //@Transactional
    @Override
    public long createUser(User user) {

        long id = user.getId();
        String name = user.getName();
        String lastname = user.getLastname();
        String middlename = user.getMiddlename();
        String login = user.getLogin();
        String password = user.getPassword();
        boolean isActive = user.isActive();
        int a =5;
        return 0;
    }

    //@Transactional
    @Override
    public User updateCredentials(long id, User user) {

        return null;
    }

    //@Transactional
    @Override
    public long blockUser(long id) {

        return 0;
    }

    @Override
    public User findUserByCredentials(String login, String password) {

        return null;
    }
}
