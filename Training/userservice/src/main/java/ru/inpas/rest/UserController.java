package ru.inpas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.inpas.dto.User;
import ru.inpas.service.UserService;
import java.util.List;

/**
 * Created by Alex on 15.02.2017.
 * Контроллер
 */
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping(value = "/users/byId/{id}", method = RequestMethod.GET)
    public User findOneById(@PathVariable("id") long id) {

        return userService.findOneById(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> findAll() {

        return userService.findAll();
    }

    @RequestMapping(value = "/users/byLogin/{login}", method = RequestMethod.GET)
    public User findAll(@PathVariable("login") String login) {

        return userService.findByLogin(login);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user) {

        return userService.create(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user) {

        return userService.update(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@RequestBody User user) {

        return userService.delete(user);
    }
}
