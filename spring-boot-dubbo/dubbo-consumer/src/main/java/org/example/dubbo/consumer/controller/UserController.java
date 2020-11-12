package org.example.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.example.dubbo.api.entity.User;
import org.example.dubbo.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference(check = false)
    private UserService userService;

    @GetMapping(value = "/user")
    public User addUser() {
        User user = new User();
        user.setName("姓名");
        user.setNick("昵称");
        User result = userService.saveUser(user);
        logger.info("User info:"+result.toString());
        return result;
    }

}
