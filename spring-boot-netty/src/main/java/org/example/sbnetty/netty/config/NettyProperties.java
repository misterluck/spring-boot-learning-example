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
     * Tcp配置
     */
    private final Http http = new Http();

    /**
     * Tcp配置
     */
    private final Https https = new Https();

    /**
     * Tcp配置
     */
    private final Tcp tcp = new Tcp();

    /**
     * Http配置
     */
    @Data
    public static class Http {

        private boolean enabled = true;

        /**
         * Tcp端口
         */
        private int port = 9999;

    }

    /**
     * Https配置
     */
    @Data
    public static class Https {

        private boolean enabled = true;

        /**
         * Tcp端口
         */
        private int port = 9443;

    }

    /**
     * Tcp配置
     */
    @Data
    public static class Tcp {

        private boolean enabled = true;

        /**
         * Tcp端口
         */
        private int port = 8888;

    }

}

