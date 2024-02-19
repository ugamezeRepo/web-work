package com.example.boot10.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.CookieRequestCache;
import com.example.boot10.filter.JwtFilter;
import com.example.boot10.handler.AuthFailHandler;
import com.example.boot10.handler.AuthSuccessHandler;

@Configuration // 설정 클래스라고 알려준다
@EnableWebSecurity // Security 를 설정하기 위한 어노테이션
public class SecurityConfig {
    @Value ("${jwt.name}")
    private String jwtName;
    @Autowired
    private JwtFilter jwtFilter;
	
	@Bean // 메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
	        AuthSuccessHandler successHandler, AuthFailHandler failHandler,
	        CookieRequestCache cookCache) throws Exception{
		// 화이트 리스트를 미리 배열에 넣어두기
        String[] whiteList = {"/", "/user/signup_form", "/user/signup", 
                "/user/loginform", "/user/login_fail", "/user/expired",};
		
		// 메소드의 매개변수에 HttpSecurity 의 참조값이 전달되는데 해당 객체를 이용해서 설정을 한다음
        return httpSecurity
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(config -> 
			config
				.requestMatchers(whiteList).permitAll() // whiteList 요청은 로그인과 상관없이 모두 허용
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
				.anyRequest().authenticated() // 위에 명시한 이외의 모든 요청은 로그인해야지 요청가능하게
		)
		.formLogin(config -> 
			config
				// 인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정 
				.loginPage("/user/required_loginform")
				// 로그인 처리를 할때 요청될 url 설정 ( spring security 가 login 처리를 대신 해준다)
				.loginProcessingUrl("/user/login")
				// 로그인 처리를 대신 하려면 어떤 파라미터명으로 username 과 password 가 넘어오는지 알려주기 
				.usernameParameter("userName") 
				.passwordParameter("password")
				.successHandler(successHandler)
				.failureHandler(failHandler)
				.permitAll() // 위에 명시한 모든 요청경로를 로그인 없이 요청할수 있도록 설정 
		)
		.logout(config ->
			config
				.logoutUrl("/user/logout")
				.logoutSuccessUrl("/")
				.deleteCookies(jwtName)
				.permitAll()
		)
		.exceptionHandling(config ->
			config.accessDeniedPage("/user/denied") // 403 forbidden 인 경우 forward 이동 시킬 경로 설정
		)
		.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		// 토큰을 검사하는 필터를 2번째 매개변수인 security 필터 이전에 동작하도록 성정
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.requestCache(config -> config.requestCache(cookCache))
		.build();
	}
	
	// 비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
	@Bean
	PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	// 인증 메니저 객체를 bean 으로 만든다. (Spring Security 가 자동 로그인 처리할때도 사용되는 객체)
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, 
			BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) throws Exception {
	    
		return http.getSharedObject(AuthenticationManagerBuilder.class) 
	      .userDetailsService(userDetailService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      .and()
	      .build();
	}
	
	// 쿠키 캐시를 bean으로 만든다.
	@Bean
	CookieRequestCache getCookieRequestCache() {
	    return new CookieRequestCache();
	}
}












