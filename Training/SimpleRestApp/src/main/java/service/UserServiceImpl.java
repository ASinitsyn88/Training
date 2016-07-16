package service;

import api.UserService;
import dao.UserDAOImpl;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация бизнес-логики
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImpl dao;

    @Override
    public User getUserById(long id) {

        System.out.println("## --- getUserById method started ---");
        System.out.println("## method input param [id:" + id + "]");
        User user = dao.getUserById(id);
        if (user == null) {
            System.out.println("## no user with [id:" + id + "]");
            return null;
        }
        if (user.isActive() == 0) {
            System.out.println("## user is blocked");
            return null;
        }
        System.out.println("## result:");
        trace(user);
        return user;
    }

    @Override
    public long createUser(User user) {

        System.out.println("## --- getUserById method started ---");
        if (user == null) {
            System.out.println("## User is null. Can not insert");
            return 0;
        }
        System.out.println("## method input param:");
        trace(user);
        long id = dao.createUser(user);
        System.out.println("## result:");
        System.out.println("## [id:" + id + "]");
        return id;
    }

    @Override
    public User updateCredentials(long id, User newUser) {

        System.out.println("## --- updateCredentials method started ---");
        if (newUser == null) {
            System.out.println("## can not update credentials, because user is null");
            return null;
        }
        System.out.println("## method input param:");
        System.out.println("## [id:" + id + "]");
        trace(newUser);
        User updatedUser = null;
        User oldUser = getUserById(id);
        if (id == oldUser.getId()) {
            oldUser.setLogin(newUser.getLogin());
            oldUser.setPassword(newUser.getPassword());
            dao.updateUser(oldUser);
            updatedUser = getUserById(id);
            System.out.println("## credentials update successfully");
        } else {
            System.out.println("## can not update credentials, because user not found");
            return null;
        }
        return updatedUser;
    }

    @Override
    public User findUserByCredentials(String login, String password) {

        System.out.println("## --- findUserByCredentials method started ---");
        System.out.println("## method input param:");
        System.out.println("## [id:" + login + "] " + "[password:" + password + "]");
        User user = dao.findUserByLoginAndPassword(login, password);
        if (user == null) {
            System.out.println("## user not found");
        }
        if (user.isActive() == 0) {
            System.out.println("## user is blocked");
            return null;
        }
        return user;
    }

    @Override
    public void blockUser(long id) {

        System.out.println("## --- blockUser method started ---");
        System.out.println("## method input param:");
        System.out.println("## [id:" + id + "]");
        User user = dao.getUserById(id);
        if (user == null) {
            System.out.println("## can not block, because user not found");
        }
        user.setActive(0);
        dao.updateUser(user);
        System.out.println("## user block successfully");
    }

    private void trace(User user) {

        long id = user.getId();
        String name = user.getName();
        String lastname = user.getLastname();
        String middlename = user.getMiddlename();
        String login = user.getLogin();
        String password = user.getPassword();
        int isActive = user.isActive();
        System.out.println("id:" + id + " name:" + name + " lastname:" + lastname + " middlename:" + middlename +
                " login:" + login + " password:" + password + " isActive:" + isActive);
    }
}
