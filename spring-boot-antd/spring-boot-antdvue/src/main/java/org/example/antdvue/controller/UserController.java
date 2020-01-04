package org.example.antdvue.controller;

import org.example.antdvue.constant.AuthConstant;
import org.example.antdvue.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity info(HttpServletRequest request) {
        String token = request.getHeader(AuthConstant.ACCESS_TOKEN);
        System.out.println("info token = " + token);

        HttpSession session = request.getSession();
        User otherUser = (User) session.getAttribute(token);

        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/nav")
    public ResponseEntity userNav(@RequestBody String body) {
        System.out.println("body = [" + body + "]");

        return ResponseEntity.ok("");
    }

}
