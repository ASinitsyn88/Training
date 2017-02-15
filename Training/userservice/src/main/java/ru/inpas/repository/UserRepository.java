package ru.inpas.repository;

import org.springframework.data.repository.CrudRepository;
import ru.inpas.dao.UserDao;
import java.util.Collection;

/**
 * Created by Alex on 15.02.2017.
 * Repository-слой для User
 */
public interface UserRepository extends CrudRepository<UserDao, Long> {

    /**
     * Найти пользователя по id
     * @param id long - идентификатор пользователя
     * @return UserDao
     */
    UserDao findOneById(long id);

    /**
     * Найти всех пользователей
     * @return Collection<UserDao>
     */
    Collection<UserDao> findAll();
}
