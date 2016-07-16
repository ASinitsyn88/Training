package mock;

import model.User;

public class UserMock {

    public static User generateUser() {

        User user = new User();
        user.setId(1);
        user.setName("Тест");
        user.setLastname("Тестов");
        user.setMiddlename("Тестович");
        user.setLogin("TestLogin");
        user.setPassword("TestPassword");
        user.setActive(true);

        return user;
    }
}
