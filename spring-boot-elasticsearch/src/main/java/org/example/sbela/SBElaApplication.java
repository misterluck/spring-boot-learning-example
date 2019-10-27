package org.example.sbela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SBElaApplication {

    public static void main(String[] args) {
        /**
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
         */
        // System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SBElaApplication.class, args);
    }

}
