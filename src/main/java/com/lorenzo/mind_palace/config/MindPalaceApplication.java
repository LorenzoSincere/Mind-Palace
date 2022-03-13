package com.lorenzo.mind_palace.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.lorenzo")
@SpringBootApplication
public class MindPalaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindPalaceApplication.class, args);
    }

}
