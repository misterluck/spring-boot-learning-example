package org.example.sbnetty.netty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhaol
 * @date 2021-08-31
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    /**
     * 请求协议
     */
    private String protocol = "http";

    /**
     * Tcp配置
     */
    private final Http http = new Http();

    /**
     * Tcp配置
     */
    private final Tcp tcp = new Tcp();

    /**
     * Http配置
     */
    @Data
    public static class Http {

        /**
         * Tcp端口
         */
        private int port = 9999;

    }

    /**
     * Tcp配置
     */
    @Data
    public static class Tcp {

        /**
         * Tcp端口
         */
        private int port = 8888;

    }

}

