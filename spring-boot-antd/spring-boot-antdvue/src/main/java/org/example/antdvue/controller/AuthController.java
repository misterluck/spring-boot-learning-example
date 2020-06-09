//package org.example.antdvue.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import org.example.antdvue.constant.AuthConstant;
//import org.example.antdvue.entity.LoginAccount;
//import org.example.antdvue.entity.User;
//import org.example.common.api.vo.Result;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.UUID;
//
//@RestController
//@RequestMapping(value = "/auth")
//public class AuthController {
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity login(HttpServletRequest request, @RequestBody LoginAccount loginAccount) {
//        Result<User> response = new Result<>();
//        String token = "4291d7da9005377ec9aec4a71ea837f";
//
//        User otherUser = new User();
//        otherUser.setId(UUID.randomUUID().toString());
//        otherUser.setName("nickName");
//        otherUser.setUsername("admin");
//        otherUser.setPassword("");
//        otherUser.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/jZUIxmJycoymBprLOUbT.png");
//        otherUser.setStatus("1");
//        otherUser.setTelephone("");
//        otherUser.setLastLoginIp("27.154.74.117");
//        otherUser.setLastLoginTime("1534837621348");
//        otherUser.setCreatorId("admin");
//        otherUser.setCreateTime("1497160610259");
//        otherUser.setDeleted("0");
//        otherUser.setRoleId("admin");
//        otherUser.setLang("zh-CN");
//        otherUser.setToken(token);
//
//        String username = "admin";
//        String password = "21232f297a57a5a743894a0e4a801fc3";
//        String email = "username@example.com";
//        String mobile = "15600009999";
//        String captcha = "354672";
//
//        if (!StringUtils.isEmpty(loginAccount.getUsername())) { // 用户名密码登录
//            if (username.equals(loginAccount.getUsername()) && password.equals(loginAccount.getPassword())) {
//
//                HttpSession session = request.getSession();
//                session.setAttribute(token, otherUser);
//
//                response.setTimestamp(System.currentTimeMillis());
//                response.setCode(200);
//                response.setMessage("");
//                response.setResult(otherUser);
//                return ResponseEntity.status(HttpStatus.OK).body(response);
//            }else {
//                otherUser.setLogin(true);
//                response.setTimestamp(System.currentTimeMillis());
//                response.setCode(401);
//                response.setMessage("账户或密码错误");
//                response.setResult(otherUser);
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//        }else if (!StringUtils.isEmpty(loginAccount.getEmail())) { // 邮箱密码登录
//            if (email.equals(loginAccount.getEmail()) && password.equals(loginAccount.getPassword())) {
//
//                HttpSession session = request.getSession();
//                session.setAttribute(token, otherUser);
//
//                response.setTimestamp(System.currentTimeMillis());
//                response.setCode(200);
//                response.setMessage("");
//                response.setResult(otherUser);
//                return ResponseEntity.status(HttpStatus.OK).body(response);
//            }else {
//                otherUser.setLogin(true);
//                response.setTimestamp(System.currentTimeMillis());
//                response.setCode(401);
//                response.setMessage("账户或密码错误");
//                response.setResult(otherUser);
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//        }else if (!StringUtils.isEmpty(loginAccount.getMobile())) { // 手机号验证码登录
//            System.out.println(loginAccount.toString());
//            if (mobile.equals(loginAccount.getMobile()) && captcha.equals(loginAccount.getCaptcha())) {
//                HttpSession session = request.getSession();
//                session.setAttribute(token, otherUser);
//
//                response.setTimestamp(System.currentTimeMillis());
//                response.setCode(200);
//                response.setMessage("");
//                response.setResult(otherUser);
//                return ResponseEntity.status(HttpStatus.OK).body(response);
//            }else {
//                otherUser.setLogin(true);
//                response.setTimestamp(System.currentTimeMillis());
//                response.setCode(401);
//                response.setMessage("验证码错误");
//                response.setResult(otherUser);
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//        }else {
//            otherUser.setLogin(true);
//            response.setTimestamp(System.currentTimeMillis());
//            response.setCode(401);
//            response.setMessage("请输入正确的用户名密码");
//            response.setResult(otherUser);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//
//
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    public ResponseEntity logout(HttpServletRequest request, @RequestBody String token) {
//        String headerToken = request.getHeader(AuthConstant.ACCESS_TOKEN);
//        System.out.println("logout token = " + headerToken);
//
//        System.out.println("token = " + token);
//        HttpSession session = request.getSession();
//        User otherUser = (User) session.getAttribute(token);
//        // System.out.println("username = "+otherUser.getUsername()+" name = "+otherUser.getName());
//        session.removeAttribute(token);
//
//        Result<String> response = new Result<>();
//        response.setTimestamp(System.currentTimeMillis());
//        response.setCode(0);
//        response.setMessage("[测试接口] 注销成功");
//        response.setResult("");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @RequestMapping(value = "/2step-code", method = RequestMethod.POST)
//    public ResponseEntity twofactor(@RequestBody String body) {
//
//        JSONObject data = new JSONObject();
//        data.put("stepCode", false);
//
//        Result<JSONObject> response = new Result<>();
//        response.setTimestamp(System.currentTimeMillis());
//        response.setCode(0);
//        response.setMessage("");
//        response.setResult(data);
//
//        return ResponseEntity.ok(response);
//    }
//
//
//}
