package org.example.sbd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 * @author zhaol
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public String test(String info) {
        info = info == null ? "123木头人" : info;
        return "{'code': '200', 'message':'"+info+"'}";
    }

}
