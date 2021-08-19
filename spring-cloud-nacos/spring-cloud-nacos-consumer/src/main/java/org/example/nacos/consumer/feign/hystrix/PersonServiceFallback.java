package org.example.nacos.consumer.feign.hystrix;

import org.example.nacos.consumer.dto.Person;
import org.example.nacos.consumer.feign.PersonService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonServiceFallback implements PersonService {

    @Override
    public List<Person> save(String name) {
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setName(name+" 没有保存成功，Person Service 故障");
        list.add(person);
        return list;
    }

}
