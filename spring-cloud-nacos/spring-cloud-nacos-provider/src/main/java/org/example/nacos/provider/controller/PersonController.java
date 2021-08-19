package org.example.nacos.provider.controller;

import org.example.nacos.provider.dto.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public List<Person> savePerson(String name) {
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setId("130210312");
        person.setName(name);
        person.setAge("24");
        person.setSex("男");
        list.add(person);
        return list;
    }

}
