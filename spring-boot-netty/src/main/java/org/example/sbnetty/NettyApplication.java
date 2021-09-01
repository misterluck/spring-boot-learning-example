package org.example.sbnetty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zhaol
 */
@SpringBootApplication
public class NettyApplication {
    private static final Logger log = LoggerFactory.getLogger(NettyApplication.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(NettyApplication.class).web(WebApplicationType.NONE).run(args);
        // SpringApplication.run(TioApplication.class, args);
    }

}
