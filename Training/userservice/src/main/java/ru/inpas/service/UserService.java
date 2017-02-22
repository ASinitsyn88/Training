package ru.inpas.service;

import ru.inpas.dto.User;
import java.util.List;

/**
 * Created by Alex on 15.02.2017.
 * Service-слой для User
 */
public interface UserService {

    /**
     * Найти пользователя по id
     * @param id long - идентификатор пользователя
     * @return User
     */
    User findOneById(long id);

    /**
     * Найти всех пользователей
     * @return List<User>
     */
    List<User> findAll();

    /**
     * Найти пользователя по логину.
     * @param login String - логин пользователя
     * @return User
     */
    User findByLogin(String login);

    /**
     * Создать пользователя
     * @param user User - объект нового пользователя
     * @return User
     */
    User create(User user);

    /**
     * Изменить пользователя
     * @param user User - объект пользователя для изменения
     * @return User
     */
    User update(User user);

    /**
     * Удалить пользователя
     * @param user User - объект пользователя для удаления
     * @return User
     */
    boolean delete(User user);
}
