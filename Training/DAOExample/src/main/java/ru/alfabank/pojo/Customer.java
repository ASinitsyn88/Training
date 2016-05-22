package ru.alfabank.pojo;

/** POJO-клиент */
public class Customer {

    /** id клиента */
    private String id;
    /** имя */
    private String name;
    /** адрес */
    private String address;
    /** город */
    private String city;
    /** email */
    private String email;

    /** Получить id */
    public String getId() {

        return id;
    }

    /** Установить id */
    public void setId(String id) {

        this.id = id;
    }

    /** Получить имя */
    public String getName() {

        return name;
    }

    /** Установить имя */
    public void setName(String name) {

        this.name = name;
    }

    /** Получить адрес */
    public String getAddress() {

        return address;
    }

    /** Установить адрес */
    public void setAddress(String address) {

        this.address = address;
    }

    /** Получить город */
    public String getCity() {

        return city;
    }

    /** Установить город */
    public void setCity(String city) {

        this.city = city;
    }

    /** Получить email */
    public String getEmail() {

        return email;
    }

    /** Установить email */
    public void setEmail(String email) {

        this.email = email;
    }

    @Override
    public String toString() {

        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
