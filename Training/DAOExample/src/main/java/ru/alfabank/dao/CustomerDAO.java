package ru.alfabank.dao;

import ru.alfabank.pojo.Customer;

/** DAO для клиента */
public interface CustomerDAO {

    /** Получить клиента по id  */
    public Customer getCustomerById(String id);
    /** Добавить клиента по id */
    public int insertCustomerById(String id);
    /** Изменить клиента по id */
    public int updateCustomerById(String id);
    /** Удалить клиента по id */
    public int deleteCustomerById(String id);
}
