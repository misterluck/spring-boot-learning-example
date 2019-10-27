package org.example.sbexa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @RequestMapping(value = "/example")
    public String example(String username, String password) {
        System.out.println("username = [" + username + "], password = [" + password + "]");
        return "{'code':'200', 'info':'success'}";
    }
}
