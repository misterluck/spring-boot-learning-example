package org.example.sbexa.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @RequestMapping(value = "/success")
    public String success(String username, String password) {
        System.out.println("username = [" + username + "], password = [" + password + "]");
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("message", "success");
        return result.toJSONString();
    }

    @RequestMapping(value = "/failed")
    public String failed(String nick, String password) {
        System.out.println("nick = [" + nick + "], password = [" + password + "]");
        JSONObject result = new JSONObject();
        result.put("code", 400);
        result.put("message", "failed");
        return result.toJSONString();
    }

    @RequestMapping(value = "/reqJson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String reqJson(@RequestBody String reqBody) {
        System.out.println(reqBody);
        return "{'code':'200', 'info':'success'}";
    }
}
