package com.example;

import com.example.member.service.MemberService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SbsApplication {
    private static final String APPLICATION= 
            "spring.config.location="+
                    "classpath:/application.yaml,"+
                    "optional:classpath:/credential.yaml";
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ctx.getBean("memberService", MemberService.class);
        
        new SpringApplicationBuilder(SbsApplication.class).properties(APPLICATION).run(args);
    }

}
