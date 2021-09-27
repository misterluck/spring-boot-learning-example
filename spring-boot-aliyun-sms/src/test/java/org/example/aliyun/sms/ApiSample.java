// This file is auto-generated, don't edit it. Thanks.
package org.example.aliyun.sms;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;

public class ApiSample {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.setEndpoint("dysmsapi.aliyuncs.com");
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.dysmsapi20170525.Client client = ApiSample.createClient("", "");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers("17600480344")
                .setSignName("测试")
                .setTemplateCode("SMS_215070443")
                .setTemplateParam("{\"code\":\"123456\"}")
                .setOutId("yourOutId");
        SendSmsResponse resp = client.sendSms(sendSmsRequest);
        SendSmsResponseBody sendSmsResponseBody = resp.getBody();
        System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(resp)));
    }
}
