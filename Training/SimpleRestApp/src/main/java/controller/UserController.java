package controller;

import model.CityForecast;
import model.Quote;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import rest.RestServiceClient;
import service.UserServiceImpl;
import soap.SOAPServiceClient;

@RestController
@RequestMapping("/myapp/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RestServiceClient restServiceClient;
    @Autowired
    private SOAPServiceClient soapServiceClient;

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public Quote getQuote() {

        logger.debug("## --- getQuote method started ---");
        return restServiceClient.getQuote();
    }

    @RequestMapping(value = "/forecast/{zip}", method = RequestMethod.GET)
    public CityForecast getForecastByZipCode(@PathVariable("zip") String zip) {

        logger.debug("## --- getForecastByZipCode method started ---");
        return soapServiceClient.getCityForecastByZip(zip);
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
