package com.example.boot09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 설정 클래스 선언 어노테이션
@EnableWebSecurity // Security 설정 어노테이션
public class SecurityConfig {
    // 화이트 리스트를 미리 배열에 넣어두기
    String[] whiteList = {"/"};

    @Bean // 메서드에서 리턴되는 SecurityFilterChain을 Bean으로 만들어준다.
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 메서드의 매개변수에 HttpSecurity 참조값이 전달되는데 해당 객체를 이용해 설정을 하고
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(config -> config.requestMatchers(whiteList).permitAll() // whiteList 요청은 로그인과 상관없이 모두 허용
                        .requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/staff/**")
                        .hasAnyRole("ADMIN", "STAFF").anyRequest().authenticated() // 위 명시한 이외의 모든 요청은 로그인 후 가능
                ).formLogin(config -> config
                        // 인증을 거치지 않은 사용자를 리다이렉트시킬 경로 설정
                        .loginPage("/user/required_loginform")
                        // 로그인 처리 시 요청될 url 설정 (spring security가 login 처리를 대신 해준다.)
                        .loginProcessingUrl("/user/login")
                        // 로그인 처리를 대신 하려면 어떤 파라미터명으로 username과 password가 넘어오는 지 알려주기
                        .usernameParameter("userName").passwordParameter("password")
                        .successHandler(new AuthSuccessHandler())
                        .failureForwardUrl("/user/login_fail").permitAll() // 위에 명시한 모든 요청 경로를 로그인 없이 요청할 수 있도록 설정
                ).logout(config -> config.logoutUrl("/user/logout") // Spring Security가 자동으로 로그아웃 처리해줄 경로 설정
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉트될 url 설정
                        .permitAll() //
                ).exceptionHandling(config ->
                // 403 Forbidden일 경우 forward 이동할 경로 설정
                config.accessDeniedPage("/user/denied"))
                .sessionManagement(config -> config.maximumSessions(1) // 최대 허용 세션 개수
                        .expiredUrl("/user/expired") // 허용 세션 개수를 넘어 로그인이 해제된 경우 리다이렉트 경로 설정
                );

        // 설정된 정보대로 SecurityFilterCahin 객체를 만들어 반환
        return httpSecurity.build();
    }

    // 비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인증 메니저 객체를 bean 으로 만든다. (Spring Security 가 자동 로그인 처리할때도 사용되는 객체)
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder).and()
                .build();
    }

}
