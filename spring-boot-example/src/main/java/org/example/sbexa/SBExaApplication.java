package org.example.sbexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SBExaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SBExaApplication.class, args);
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|");
    }
}
