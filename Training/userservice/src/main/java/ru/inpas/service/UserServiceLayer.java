package ru.inpas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inpas.dao.UserDao;
import ru.inpas.dto.User;
import ru.inpas.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Alex on 15.02.2017.
 * Реализация Service-слоя для User
 */
@Service
public class UserServiceLayer implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceLayer(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public User findOneById(long id) {

        try {
            return userRepository.findOneById(id);
        } catch (Exception e) {
            System.out.println("Exception during get data from DB");
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {

        try {
            return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Exception during get data from DB");
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User findByLogin(String login) {

        if (login == null) {
            System.out.println("Login can not be null");
            return null;
        }
        try {
            return userRepository.findByLogin(login);
        } catch (Exception e) {
            System.out.println("Exception during get data from DB");
            return null;
        }
    }

    @Transactional
    @Override
    public User create(User user) {

        if (user == null) {
            System.out.println("User can not be null");
            return null;
        }
        if (user.getId() != null) {
            System.out.println("You must invoke update method");
            return null;
        }
        try {
            return userRepository.save(new UserDao(user));
        } catch (Exception e) {
            System.out.println("Exception during get data from DB");
            return null;
        }
    }

    @Transactional
    @Override
    public User update(User user) {

        if (user == null) {
            System.out.println("User can not be null");
            return null;
        }
        if (user.getId() == null) {
            System.out.println("You must invoke create method");
            return null;
        }
        try {
            User oneById = userRepository.findOneById(user.getId());
            if (oneById == null) {
                System.out.println("Can not find user by id " + user.getId());
                return null;
            }
            return userRepository.save(new UserDao(user));
        } catch (Exception e) {
            System.out.println("Exception during get data from DB");
            return null;
        }
    }

    @Transactional
    @Override
    public boolean delete(User user) {

        if (user == null) {
            System.out.println("User can not be null");
            return false;
        }
        if (user.getId() == null) {
            System.out.println("Can not delete user without id");
            return false;
        }
        try {
            User oneById = userRepository.findOneById(user.getId());
            if (oneById == null) {
                System.out.println("Can not find user by id " + user.getId());
                return false;
            }
            userRepository.delete(new UserDao(oneById));
            return true;
        } catch (Exception e) {
            System.out.println("Exception during get data from DB");
            return false;
        }
    }
}
