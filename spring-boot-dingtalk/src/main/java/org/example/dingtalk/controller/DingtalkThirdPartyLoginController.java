//package org.example.dingtalk.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.dingtalk.api.DefaultDingTalkClient;
//import com.dingtalk.api.DingTalkClient;
//import com.dingtalk.api.request.OapiGettokenRequest;
//import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
//import com.dingtalk.api.request.OapiUserGetRequest;
//import com.dingtalk.api.request.OapiUserGetUseridByUnionidRequest;
//import com.dingtalk.api.response.OapiGettokenResponse;
//import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
//import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse.UserInfo;
//import com.dingtalk.api.response.OapiUserGetResponse;
//import com.dingtalk.api.response.OapiUserGetUseridByUnionidResponse;
//import com.taobao.api.ApiException;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class DingtalkThirdPartyLoginController {
//
//    @RequestMapping(value = "/dingtalkRedirect")
//    public String dingtalkLoginRedirect(String code, String state) throws ApiException {
//        System.out.println("code = [" + code + "], state = [" + state + "]");
//
//        DefaultDingTalkClient snsClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
//        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
//        req.setTmpAuthCode(code);
//        String appId = "dingoawceybfpfpz7ldmok";
//        String accessSecret = "r07N3WZmeB77swPACAUz_5Ng3uOqTuBlRsSusOWwvXWO4OmXgl4OTGibXeFxrGZs";
//        OapiSnsGetuserinfoBycodeResponse snsResponse = snsClient.execute(req,appId,accessSecret);
//        UserInfo userInfo = snsResponse.getUserInfo();
//
//        System.out.println(userInfo.getNick()+"\t"+userInfo.getOpenid()+"\t"+userInfo.getUnionid());
//
//        /*DefaultDingTalkClient tokenClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
//        OapiGettokenRequest request = new OapiGettokenRequest();
//        request.setAppkey(accessKey);
//        request.setAppsecret(accessSecret);
//        request.setHttpMethod("GET");
//        OapiGettokenResponse tokenResponse = tokenClient.execute(request);
//        String token = tokenResponse.getAccessToken();
//
//        System.out.println("token:"+token);*/
//
//        /*DingTalkClient uidClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getUseridByUnionid");
//        OapiUserGetUseridByUnionidRequest uidRequest = new OapiUserGetUseridByUnionidRequest();
//        uidRequest.setUnionid(userInfo.getUnionid());
//        uidRequest.setHttpMethod("GET");
//
//        OapiUserGetUseridByUnionidResponse uidResponse = uidClient.execute(uidRequest, token);
//
//        String userId = uidResponse.getUserid();*/
//
//        /*DingTalkClient userClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
//        OapiUserGetRequest userRequest = new OapiUserGetRequest();
//        userRequest.setUserid(userId);
//        userRequest.setHttpMethod("GET");
//        OapiUserGetResponse userResponse = userClient.execute(userRequest, accessKey, accessSecret);
//        System.out.println(JSON.toJSONString(userResponse));*/
//        return "";
//    }
//
//    @RequestMapping(value = "/get/code")
//    public String dingtalkLoginGetCode() {
//
//        return "";
//    }
//
//}
