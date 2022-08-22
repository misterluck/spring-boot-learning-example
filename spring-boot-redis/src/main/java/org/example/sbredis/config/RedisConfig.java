package org.example.sbredis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.context.annotation.Scope;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    /*@Bean
    @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    @Scope(value = "prototype")
    public GenericObjectPoolConfig redisPool(){
        return new GenericObjectPoolConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.redis")
    public RedisStandaloneConfiguration defaultRConfig(){
        return new RedisStandaloneConfiguration();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(GenericObjectPoolConfig config, RedisStandaloneConfiguration defaultRConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(config).commandTimeout(Duration.ofMillis(config.getMaxWaitMillis())).build();
        return new LettuceConnectionFactory(defaultRConfig, clientConfiguration);
    }*/

    /**
     * 更改Redis序列化
     * RedisTemplate默认使用JdkSerializationRedisSerializer
     * StringRedisTemplate默认使用StringRedisSerializer
     * Spring Data提供了：
     *      GenericToStringSerializer、Jackson2JsonRedisSerializer
     *      JacksonJsonRedisSerializer、JdkSerializationRedisSerializer
     *      OxmSerializer、StringRedisSerializer
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        KryoRedisSerializer<Object> kryoRedisSerializer = new KryoRedisSerializer<Object>(Object.class);

        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(kryoRedisSerializer);
        template.setHashKeySerializer(jdkSerializationRedisSerializer);
        template.setHashValueSerializer(kryoRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

}
