package ru.inpas.dao;

import ru.inpas.dto.User;
import javax.persistence.*;

/**
 * Created by Alex on 15.02.2017.
 * DAO-слой для структуры данных User
 */
@Entity
@Table(name = "users", schema = "inpas")
public class UserDao extends User {

    public UserDao() {

        super();
    }

    public UserDao(User user) {

        super();
        if (user == null) {
            throw new IllegalArgumentException("User can not be null");
        }
        this.setId(user.getId());
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
        this.setPatronymic(user.getPatronymic());
        this.setPhone(user.getPhone());
        this.setLogin(user.getLogin());
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "sqn_users", sequenceName = "inpas.sqn_users")
    @GeneratedValue(generator = "sqn_users", strategy = GenerationType.SEQUENCE)
    @Override
    public Long getId() {

        return super.getId();
    }

    @Column(name = "firstname", nullable = false, length = 20)
    @Override
    public String getFirstname() {

        return super.getFirstname();
    }

    @Column(name = "lastname", nullable = false, length = 20)
    @Override
    public String getLastname() {

        return super.getLastname();
    }

    @Column(name = "patronymic", nullable = false, length = 20)
    @Override
    public String getPatronymic() {

        return super.getPatronymic();
    }

    @Column(name = "phone", nullable = false, length = 11)
    @Override
    public String getPhone() {

        return super.getPhone();
    }

    @Column(name = "login", nullable = false, length = 20)
    @Override
    public String getLogin() {

        return super.getLogin();
    }
}
