package gov.zwfw.iam.service.impl;

import gov.zwfw.iam.plugin.PluginApiImpl;
import gov.zwfw.iam.service.PluginBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PluginBusinessServiceImpl implements PluginBusinessService {

    private Logger logger = LoggerFactory.getLogger(PluginBusinessServiceImpl.class);
    //注入 Ribbon 负载均衡器对象
    //在引入 spring-cloud-starter-alibaba-nacos-discovery自动引入spring-cloud-starter-netflix-ribbon 后在 SpringBoot 启动时会自动实例化 LoadBalancerClient 对象。
    //在 Controlle 使用 @Resource 注解进行注入即可。
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    //将应用启动时创建的 RestTemplate 对象注入 ConsumerController
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getNick(String nick) {

        //此处serviceId与uri是通过获取配置管理模块的策略来实现调用自带的默认插件还是自定义插件
        String serviceId = "demo-plugin-default";
        String uri = "/getNick";
        //loadBalancerClient.choose()方法会从 Nacos 获取 provider-service 所有可用实例，
        //并按负载均衡策略从中选择一个可用实例，封装为 ServiceInstance（服务实例）对象
        //结合现有环境既是从192.168.31.111:80、192.168.31.112:80、192.168.31.113:80三个实例中选择一个包装为ServiceInstance
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        String scheme = serviceInstance.getScheme();
        //获取服务实例的 IP 地址
        String host = serviceInstance.getHost();
        //获取服务实例的端口
        int port = serviceInstance.getPort();
        //在日志中打印服务实例信息
        logger.info("本次调用由provider-service的" + host + ":" + port + " 实例节点负责处理" );
        //通过 RestTemplate 对象的 getForObject() 方法向指定 URL 发送请求，并接收响应。
        //getForObject()方法有两个参数：
        //1. 具体发送的 URL，结合当前环境发送地址为：http://192.168.31.111:80/provider/msg
        //2. String.class说明 URL 返回的是纯字符串，如果第二参数是实体类， RestTemplate 会自动进行反序列化，为实体属性赋值
        //String result = restTemplate.getForObject("http://" + host + ":" + port + uri, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> params = new HashMap<>();
        params.put("nick", nick);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params, headers);
        String result = restTemplate.postForObject("http://" + host + ":" + port + uri, request, String.class);
        //输出响应内容
        logger.info("provider-service 响应数据:" + result);

        return result;
    }
}
