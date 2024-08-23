package exercise.controller;

import exercise.daytime.Daytime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    private Daytime daytime;

    @GetMapping("/welcome")
    public String index() {
        return String.format("It is %s now! Welcome to Spring!", daytime.getName());
    }

}
// END
