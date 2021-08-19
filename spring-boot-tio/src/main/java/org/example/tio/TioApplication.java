package org.example.tio;

import org.example.tio.config.ApplicationProperties;
import org.example.tio.tio.client.ClientMessage;
import org.example.tio.tio.server.ServerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TioApplication {

    private static final Logger log = LoggerFactory.getLogger(TioApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TioApplication.class, args);
    }

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private ServerMessage serverMessage;

    @Autowired
    private ClientMessage clientMessage;

    @Bean(name = "message")
    public Object getMessage() {
        if (properties.getTio().getServer()) {
            log.info("server");
            return serverMessage.start();
        } else {
            log.info("client");
            return clientMessage.start();
        }
    }

}
