package org.example.proguard.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.proguard.entity.City;
import org.example.proguard.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/city")
    public String city() {
        List<City> list = cityService.getCityAll();
        return JSONObject.toJSONString(list);
    }

}
