package org.example.dubbo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.dubbo.entity.User;
import org.example.dubbo.api.UserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @DubboReference(check = false)
    private UserApi userApi;

    @GetMapping(value = "/user")
    public User addUser() {
        User user = new User();
        user.setName("姓名");
        user.setNick("昵称");
        User result = userApi.saveUser(user);
        logger.info("User info:"+result.toString());
        return result;
    }

}
