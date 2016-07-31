package controller;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserServiceImpl;

@RestController
@RequestMapping("/myapp/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") long id) {

        logger.debug("## --- getUser method started ---");
        return userService.getUserById(id);
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public long createUser(@RequestBody User user) {

        logger.debug("## --- createUser method started ---");
        return userService.createUser(user);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
    public User updateCredentials(@PathVariable("id") long id, @RequestBody User user) {

        logger.debug("## --- updateCredentials method started ---");
        return userService.updateCredentials(id, user);
    }


    @RequestMapping(value="/find", method = RequestMethod.PUT)
    public User findUserByCredentials(@RequestParam(value = "login", required = true) String login,
                                      @RequestParam(value = "password", required = true) String password) {

        logger.debug("## --- findUserByCredentials method started ---");
        return userService.findUserByCredentials(login, password);
    }

    @RequestMapping(value="/{id}/block", method = RequestMethod.GET)
    public void blockUser(@PathVariable("id") long id) {

        logger.debug("## --- blockUser method started ---");
        userService.blockUser(id);
    }
}
