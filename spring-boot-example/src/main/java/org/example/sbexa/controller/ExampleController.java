package org.example.sbexa.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @RequestMapping(value = "/example")
    public String example(String username, String password) {
        System.out.println("username = [" + username + "], password = [" + password + "]");
        return "{'code':'200', 'info':'success'}";
    }

    @RequestMapping(value = "/reqJson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String reqJson(@RequestBody String reqBody) {
        System.out.println(reqBody);
        return "{'code':'200', 'info':'success'}";
    }
}
