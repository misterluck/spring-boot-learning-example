//package org.example.antdvue.controller;
//
//import org.example.antdvue.entity.LoginAccount;
//import org.example.common.api.vo.Result;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/account")
//public class AccountController {
//
//    @RequestMapping(value = "/sms")
//    public ResponseEntity smsCaptcha(@RequestBody LoginAccount loginAccount) {
//        Result<LoginAccount> response = new Result<>();
//
//        System.out.println(loginAccount.getMobile());
//        LoginAccount data = new LoginAccount();
//        data.setCaptcha("354672");
//
//        response.setTimestamp(System.currentTimeMillis());
//        response.setCode(0);
//        response.setMessage("");
//        response.setResult(data);
//        return ResponseEntity.ok(response);
//    }
//
//}
