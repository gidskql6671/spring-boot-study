package com.example;

import com.example.member.service.MemberService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SbsApplication {
    private static final String APPLICATION= 
            "spring.config.location="+
                    "classpath:/application.yaml,"+
                    "optional:classpath:/credential.yaml";
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        
        new SpringApplicationBuilder(SbsApplication.class).properties(APPLICATION).run(args);
    }

}
