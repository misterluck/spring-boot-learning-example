package org.example.antdvue.controller;

import org.example.antdvue.entity.Response;
import org.example.antdvue.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<User> login(@RequestBody User user) {
        Response<User> response = new Response<>();

        System.out.println("username = " + user.getUsername() + " password = " + user.getPassword());

        User otherUser = new User();
        otherUser.setId(UUID.randomUUID().toString());
        otherUser.setName("nickName");
        otherUser.setUsername("admin");
        otherUser.setPassword("");
        otherUser.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/jZUIxmJycoymBprLOUbT.png");
        otherUser.setStatus("1");
        otherUser.setTelephone("");
        otherUser.setLastLoginIp("27.154.74.117");
        otherUser.setLastLoginTime("1534837621348");
        otherUser.setCreatorId("admin");
        otherUser.setCreateTime("1497160610259");
        otherUser.setDeleted("0");
        otherUser.setRoleId("admin");
        otherUser.setLang("zh-CN");
        otherUser.setToken("4291d7da9005377ec9aec4a71ea837f");


        response.setTimestamp(System.currentTimeMillis());
        response.setCode("200");
        response.setMessage("");
        response.setResult(otherUser);

        return response;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(String username, String password) {
        System.out.println("username = " + username + "password = " + password);
        return "";
    }

}
