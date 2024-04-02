package com.spring.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf( (csrf)-> csrf.disable() );
        http.authorizeHttpRequests((authorize) -> //특정 페이지에 로그인 검사 하는거임
                authorize.requestMatchers("/**").permitAll()
        );
        http.formLogin((formLogin) ->
                formLogin.loginPage("/api/login")
                        .defaultSuccessUrl("http://localhost:3000/")
        );
        http.logout(logout ->
                logout.logoutUrl("/api/logout")
                        .logoutSuccessUrl("http://localhost:3000/")
                        .invalidateHttpSession(true) // HTTP 세션 무효화 여부 설정
                        .deleteCookies("JSESSIONID") // 로그아웃 시 삭제할 쿠키 설정
        );
        return http.build();
    }
}