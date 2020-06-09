package org.example.antdvue.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaolei
 * @className ApplicationConfig
 * @description 配置类
 * @date 2020/6/9 23:06
 */
@Configuration
@ComponentScan(basePackages = {"com.ai.common", "com.ai.system"})
public class ApplicationConfig {
}

