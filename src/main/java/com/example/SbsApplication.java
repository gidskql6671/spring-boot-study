package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SbsApplication {
    private static final String APPLICATION= 
            "spring.config.location="+
                    "classpath:/application.yaml,"+
                    "optional:classpath:/credential.yaml";
    public static void main(String[] args) {
        new SpringApplicationBuilder(SbsApplication.class).properties(APPLICATION).run(args);
        
//        SpringApplication.run(SbsApplication.class, args);
    }

}
