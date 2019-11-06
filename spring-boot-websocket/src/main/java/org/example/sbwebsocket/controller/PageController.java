package org.example.sbwebsocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/server1")
    public String server1(){
        return "server1";
    }

    @RequestMapping("/server2")
    public String showPage(){
        return "server2";
    }

}
