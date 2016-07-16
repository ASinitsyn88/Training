package dao;

import model.User;

public interface UserDAO {

    /**
     * Получить пользователя по id
     * @param id - id пользователя
     * @return - объект пользователя
     */
    public User getUserById(long id);

    /**
     * Создать пользователя
     * @param user - объект пользователя
     * @return - id нового пользователя
     */
    public long createUser(User user);

    /**
     * Изменить пользователя
     * @param user - объект пользователя
     */
    public void updateUser(User user);

    /**
     * Найти пользователя по логину и паролю
     * @param login - логин
     * @param password - пароль
     * @return - объект пользователя
     */
    public User findUserByLoginAndPassword(String login, String password);
}
