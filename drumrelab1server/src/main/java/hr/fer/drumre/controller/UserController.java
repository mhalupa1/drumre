package hr.fer.drumre.controller;

import hr.fer.drumre.model.OAuthData;
import hr.fer.drumre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping(value = "/login")
    public String login() throws TwitterException {
        return service.login();
    }

    @RequestMapping(value = "/twitterCallback", method = RequestMethod.POST)
    public AccessToken getTwitterCallback(@RequestBody OAuthData data) throws TwitterException {
        return service.getTwitterCallback(data);
    }
}
