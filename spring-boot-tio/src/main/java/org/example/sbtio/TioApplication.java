package org.example.sbtio;

import org.example.sbtio.config.ApplicationProperties;
import org.example.sbtio.tio.client.ClientMessage;
import org.example.sbtio.tio.server.ServerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author zhaolei
 */
@SpringBootApplication
public class TioApplication {

    private static final Logger log = LoggerFactory.getLogger(TioApplication.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(TioApplication.class).web(WebApplicationType.NONE).run(args);
        // SpringApplication.run(TioApplication.class, args);
    }

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private ServerMessage serverMessage;

    @Autowired
    private ClientMessage clientMessage;

    @Bean(name = "server")
    public Object getServerMessage() {
        if (properties.getServer().getEnabled()) {
            log.info("server");
            return serverMessage.start();
        } else {
            return null;
        }
    }

    @Bean(name = "client")
    public Object getClientMessage() {
        if (properties.getClient().getEnabled()) {
            log.info("client");
            return clientMessage.start();
        } else {
            return null;
        }
    }

}
