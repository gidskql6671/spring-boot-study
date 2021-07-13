package com.study.dong.member.config;

import com.study.dong.member.service.MemberService;
import com.study.dong.member.service.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MemberService memberService;

    // @Configuration 클래스 안에서 @Bean 어노테이션을 통해 빈 객체를 만들 수 있음.
    // PasswordEncoder 타입을 가지는 passwordEncoder라는 이름의 빈 객체가 만들어짐.
    // 해당 빈 객체는 함수에서 반환되는 값, 여기서는 new BCryptPasswordEncoder() 객체가 들어감.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        // 인증을 무시하기 위한 설정
        // 아래 패턴들은 인증없이 접근 가능해짐.
        // 파일의 기준 경로는 resources/static 임
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/board").permitAll() // 해당 url 패턴에 대해 인증이 되지 않아도 접근을 허용
                .antMatchers("/member/**").authenticated() // 해당 url 패턴에 대해 인증이 된 사용자만 접근 허용
                .antMatchers("/admin").hasRole(Role.ADMIN.getValue()) // 해당 url 패턴에 대해 ADMIN 역할을 가진 사용자만 접근 허용
                .and()
            .formLogin()  // 로그인 설정
                .loginPage("/login")
                .usernameParameter("userId")
                .passwordParameter("userPassword")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
            .logout()   // 로그아웃 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)   // 세션 초기화
                .and()
            .exceptionHandling();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
        // 로그인 처리를 하기 위한 AuthenticationManagerBuilder를 설정
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
