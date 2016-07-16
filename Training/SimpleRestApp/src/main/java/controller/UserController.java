package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserServiceImpl;

@RestController
@RequestMapping("/myapp/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") long id) {

        return userService.getUserById(id);
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public long createUser(@RequestBody User user) {

        return userService.createUser(user);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
    public User updateCredentials(@PathVariable("id") long id, @RequestBody User user) {

        return userService.updateCredentials(id, user);
    }

    @RequestMapping(value="/block/{id}", method = RequestMethod.PUT)
    public long blockUser(@PathVariable("id") long id) {

        return userService.blockUser(id);
    }

    @RequestMapping(value="/find", method = RequestMethod.PUT)
    public User findUserByCredentials(@RequestParam(value = "login", required = true) String login,
                                      @RequestParam(value = "password", required = true) String password) {

        return userService.findUserByCredentials(login, password);
    }
}
