package com.example.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hello.auto.Car;
import com.example.hello.auto.MyCar;
import com.example.hello.dao.MemberDao;
import com.example.hello.dao.MemberDaoImpl;

@Configuration
public class BeanConfig {

	/*
	 * 이 메서드에서 리턴되는 객체를 spring이 관리하는 bean이 되도록 한다. 메서드명이 이 객체의 이름으로 부여된다. 이 메서드는 한
	 * 번만 호출된다.(single ton)
	 */
	@Bean
	public Car myCar() {
		System.out.println("BeanConfig 클래스의 myCar() 호출됨.");
		Car c1 = new MyCar();

		return c1;
	}

	// MemberDao type 리턴 메서드
	@Bean
	public MemberDao memberDao() {

		return new MemberDaoImpl();
	}
}
