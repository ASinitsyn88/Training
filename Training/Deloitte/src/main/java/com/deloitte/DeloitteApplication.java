package com.deloitte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.deloitte.*")
@ComponentScan("com.deloitte.*")
@SpringBootApplication
public class DeloitteApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DeloitteApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DeloitteApplication.class);
    }
}