package org.example.proguard.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.proguard.entity.AuthInfoAdmin;
import org.example.proguard.entity.City;
import org.example.proguard.service.CityService;
import org.example.proguard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/city")
    public String city() {
        List<City> list = cityService.getCityAll();
        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value = "/hello")
    public String hello(String hello) {
        hello = StringUtils.isEmpty(hello) ? "Hello World!" : hello;
        return hello;
    }

    @RequestMapping(value = "/user")
    public String user(String status) {
        status = StringUtils.isEmpty(status) ? "1" : status;
        AuthInfoAdmin authInfoAdmin = new AuthInfoAdmin();
        authInfoAdmin.setAdminStatus(status);
        List<AuthInfoAdmin> list = userService.queryAuthInfoAdminByAttribute(authInfoAdmin);
        return JSONObject.toJSONString(list);
    }

}
