package ru.inpas.dto;

import com.google.gson.Gson;
import org.springframework.data.annotation.Id;

/**
 * Created by Alex on 15.02.2017.
 * Структура данных "Пользователь"
 */
public class User {

    public User() {

    }

    public User(Long id, String firstname, String lastname, String patronymic, String phone, String login) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.login = login;
    }

    private Long id;

    private String firstname;

    private String lastname;

    private String patronymic;

    private String phone;

    private String login;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public String getPatronymic() {

        return patronymic;
    }

    public void setPatronymic(String patronymic) {

        this.patronymic = patronymic;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    @Override
    public String toString() {

        return new Gson().toJson(this);
    }
}
