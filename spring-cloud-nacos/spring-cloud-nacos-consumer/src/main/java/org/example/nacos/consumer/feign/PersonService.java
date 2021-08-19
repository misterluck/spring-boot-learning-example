package org.example.nacos.consumer.feign;

import org.example.nacos.consumer.dto.Person;
import org.example.nacos.consumer.feign.hystrix.PersonServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "spring-cloud-nacos-provider", fallback = PersonServiceFallback.class)
public interface PersonService {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    List<Person> save(String name);

}
