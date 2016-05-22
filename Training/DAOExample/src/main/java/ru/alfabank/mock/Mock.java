package ru.alfabank.mock;

import ru.alfabank.pojo.Customer;

/** Класс для создания заглушек */
public class Mock {

    public static Customer generateCustomerMock(String id) {

        Customer cus = new Customer();
        cus.setId(id);
        cus.setName("TEST_NAME");
        cus.setCity("TEST_CITY");
        cus.setAddress("TEST_ADDRESS");
        cus.setEmail("TEST_EMAIL@MAIL.COM");

        return cus;
    }
}
