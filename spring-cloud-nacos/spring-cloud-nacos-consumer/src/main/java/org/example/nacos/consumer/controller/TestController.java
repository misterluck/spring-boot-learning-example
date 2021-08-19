package org.example.nacos.consumer.controller;

import org.example.nacos.consumer.dto.Person;
import org.example.nacos.consumer.feign.PersonService;
import org.example.nacos.consumer.feign.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private PersonService personService;
    @Autowired
    private SomeService someService;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public List<Person> savePerson() {
        List<Person> list = personService.save("赵雷");
        String message = someService.getSome();
        System.out.println("######  message = [" + message + "]");
        return list;
    }

}
