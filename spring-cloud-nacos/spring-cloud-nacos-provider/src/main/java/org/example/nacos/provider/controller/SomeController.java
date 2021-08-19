package org.example.nacos.provider.controller;

import org.example.nacos.provider.dto.Some;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {

    /*@Value(value = "${my.message}")
    private String message;*/

    @RequestMapping(value = "/getSome")
    public Some getSome(@RequestBody Some some) {
        System.out.println("######  message = [" + some.getMessage() + "]");
        some.setMessage("你好!");
        return some;
    }

}
