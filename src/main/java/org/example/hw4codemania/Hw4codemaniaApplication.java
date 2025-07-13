package org.example.hw4codemania;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Hw4codemaniaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw4codemaniaApplication.class, args);
        LoggerFactory.getLogger(Hw4codemaniaApplication.class).info("Hello World!");
    }

}
