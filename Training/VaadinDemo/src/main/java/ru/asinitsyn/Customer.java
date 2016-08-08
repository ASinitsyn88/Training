package ru.asinitsyn;

import java.io.Serializable;
import java.util.Date;

/**
 * Структура данных "Клиент",получаемая от фейкового бекенда
 */
@SuppressWarnings("serial")
public class Customer implements Serializable, Cloneable {

    // Уникальный идентификатор
    private Long id;
    // Имя
    private String firstName = "";
    // Фамилия
    private String lastName = "";
    // Дата рождения
    private Date birthDate;
    // Статус клиента
    private CustomerStatus status;
    // Электронная почта
    private String email = "";

    /**
     * Получить уникальный идентификатор
     * @return
     */
    public Long getId() {

        return id;
    }

    /**
     * Установить уникальный идентификатор
     * @param id
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * Получить имя
     * @return
     */
    public String getFirstName() {

        return firstName;
    }

    /**
     * Установить имя
     * @param firstName
     */
    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    /**
     * Получить фамилию
     * @return
     */
    public String getLastName() {

        return lastName;
    }

    /**
     * Установить фамилию
     * @param lastName
     */
    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    /**
     * Получить электронную почту
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Установить электронную почту
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Получить статус клиента
     * @return
     */
    public CustomerStatus getStatus() {
        return status;
    }

    /**
     * Установить статус клиента
     * @param status
     */
    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    /**
     * Получить дату рождения
     * @return
     */
    public Date getBirthDate() {

        return birthDate;
    }

    /**
     * Установить дату рождения
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {

        this.birthDate = birthDate;
    }

    /**
     * Проверка наличия id у клиента
     * @return
     */
    public boolean isPersisted() {

        return id != null;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }
        // Если 2 объекта одного класса имеют одинаковые id - то они равны
        if (obj instanceof Customer && obj.getClass().equals(getClass())) {
            return this.id.equals(((Customer) obj).id);
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    /**
     * Клонировать текущий объект
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Customer clone() throws CloneNotSupportedException {

        return (Customer) super.clone();
    }

    @Override
    public String toString() {

        return firstName + " " + lastName;
    }
}
