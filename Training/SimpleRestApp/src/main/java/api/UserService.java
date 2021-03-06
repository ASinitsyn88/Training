package api;

import model.User;

public interface UserService {

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
     * Изменить авторизационные данные(логин и пароль)
     * @param id - id пользователя
     * @param user - объект пользователя
     */
    public User updateCredentials(long id, User user);


    /**
     * Найти пользователя по логину и паролю
     * @param login - логин
     * @param password - пароль
     * @return - объект пользователя
     */
    public User findUserByCredentials(String login, String password);

    /**
     * Заблокировать пользователя по id
     * @param id - id пользователя
     */
    public void blockUser(long id);
}
