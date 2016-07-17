package service;

import api.UserService;
import dao.UserDAOImpl;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация бизнес-логики
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserDAOImpl dao;

    @Override
    public User getUserById(long id) {

        logger.debug("## method input param [id:" + id + "]");
        User user = dao.getUserById(id);
        if (user == null) {
            logger.debug("## no user with [id:" + id + "]");
            return null;
        }
        if (user.isActive() == 0) {
            logger.debug("## user is blocked");
            return null;
        }
        logger.debug("## result:");
        trace(user);
        return user;
    }

    @Override
    public long createUser(User user) {

        if (user == null) {
            logger.debug("## User is null. Can not insert");
            return 0;
        }
        logger.debug("## method input param:");
        trace(user);
        long id = dao.createUser(user);
        logger.debug("## result:");
        logger.debug("## [id:" + id + "]");
        return id;
    }

    @Override
    public User updateCredentials(long id, User newUser) {

        if (newUser == null) {
            logger.debug("## can not update credentials, because user is null");
            return null;
        }
        logger.debug("## method input param:");
        logger.debug("## [id:" + id + "]");
        trace(newUser);
        User updatedUser = null;
        User oldUser = getUserById(id);
        if (id == oldUser.getId()) {
            oldUser.setLogin(newUser.getLogin());
            oldUser.setPassword(newUser.getPassword());
            dao.updateUser(oldUser);
            updatedUser = getUserById(id);
            logger.debug("## credentials update successfully");
        } else {
            logger.debug("## can not update credentials, because user not found");
            return null;
        }
        return updatedUser;
    }

    @Override
    public User findUserByCredentials(String login, String password) {

        logger.debug("## method input param:");
        logger.debug("## [id:" + login + "] " + "[password:" + password + "]");
        User user = dao.findUserByLoginAndPassword(login, password);
        if (user == null) {
            logger.debug("## user not found");
        }
        if (user.isActive() == 0) {
            logger.debug("## user is blocked");
            return null;
        }
        return user;
    }

    @Override
    public void blockUser(long id) {

        logger.debug("## method input param:");
        logger.debug("## [id:" + id + "]");
        User user = dao.getUserById(id);
        if (user == null) {
            logger.debug("## can not block, because user not found");
        }
        user.setActive(0);
        dao.updateUser(user);
        logger.debug("## user block successfully");
    }

    private void trace(User user) {

        long id = user.getId();
        String name = user.getName();
        String lastname = user.getLastname();
        String middlename = user.getMiddlename();
        String login = user.getLogin();
        String password = user.getPassword();
        int isActive = user.isActive();
        logger.debug("id:" + id + " name:" + name + " lastname:" + lastname + " middlename:" + middlename +
                " login:" + login + " password:" + password + " isActive:" + isActive);
    }
}
