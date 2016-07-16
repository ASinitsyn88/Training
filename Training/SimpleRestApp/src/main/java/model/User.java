package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Структура данных "Пользователь"
 */
@XmlRootElement
public class User {

    // id
    private long id;
    // имя
    private String name;
    // фамилия
    private String lastname;
    // отчество
    private String middlename;
    // логин
    private String login;
    // пароль
    private String password;
    // признак: активен/заблокирован
    private int isActive;

    /**
     * Получить id
     * @return
     */
    public long getId() {

        return id;
    }

    /**
     * Установить id
     * @param id
     */
    public void setId(long id) {

        this.id = id;
    }

    /**
     * Получить имя
     * @return
     */
    public String getName() {

        return name;
    }

    /**
     * Установить имя
     * @param name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Получить фамилию
     * @return
     */
    public String getLastname() {

        return lastname;
    }

    /**
     * Установить фамилию
     * @param lastname
     */
    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    /**
     * Получить отчество
     * @return
     */
    public String getMiddlename() {

        return middlename;
    }

    /**
     * Установить отчество
     * @param middlename
     */
    public void setMiddlename(String middlename) {

        this.middlename = middlename;
    }

    /**
     * Получить логин
     * @return
     */
    public String getLogin() {

        return login;
    }

    /**
     * Установить логин
     * @param login
     */
    public void setLogin(String login) {

        this.login = login;
    }

    /**
     * Получить пароль
     * @return
     */
    public String getPassword() {

        return password;
    }

    /**
     * Установить пароль
     * @param password
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * Получить признак: активен/заблокирован
     * @return
     */
    public int isActive() {

        return isActive;
    }

    /**
     * Установить признак: активен/заблокирован
     * @param isActive
     */
    public void setActive(int isActive) {

        this.isActive = isActive;
    }

    @Override
    public String toString() {

        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
