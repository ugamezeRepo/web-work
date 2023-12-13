package com.example.boot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.boot01.service.HomeService;

@SpringBootApplication
public class Boot01BeanApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Boot01BeanApplication.class, args);

		// 태정이 형의 집을 청소하려면?
		HomeService s1 = ctx.getBean(HomeService.class);
		s1.clean("송태정");
		// 혜란이 집의 빨래를 하려면?
		s1.wash("김혜란");
		// 바닥을 뚫으러면?
		s1.hole("바닥");
	}

}
