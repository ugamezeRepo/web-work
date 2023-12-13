package com.example.hello;

/**
 * 	[ 객체들 간에 의존관계를 느슨하게 하는 원칙 ]
 * 
 * 1. 핵심 의존객체를 직접 생성하지 않고 spring에게 객체 생성 및 관리를 맡긴다.
 * 2. 필요한 객체가 있을 경우 spring으로부터 받아서 사용한다.
 * 3. 인터페이스 type을 적극 활용한다. 
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.hello.auto.Car;
import com.example.hello.dao.MemberDao;
import com.example.hello.dto.MemberDto;

@SpringBootApplication
public class HelloBootApplication {

	public static void main(String[] args) {

		// run() 메서드가 리턴하는 ApplicationContext 객체의 참조값을 변수에 담고
		ApplicationContext ctx = SpringApplication.run(HelloBootApplication.class, args);

		// 만약 달리고 싶다면 어떻게?
		// 1. 달리는 기능을 가지고 있는 객체를 생성해 참조값을 얻어낸다.
//		MyCar car1 = new MyCar();
		// 2. 얻어낸 참조값을 이용해 달린다.
//		car1.drive();

		// 위와 동일한 작업을 spring을 이용해 작업
		// spring이 관리하는 객체 중 Car type을 찾아 가져오기
		Car car2 = ctx.getBean(Car.class); // 동일한 type이 2개 이상 있으면 예외 발생!
		car2.drive();
		// spring이 관리하는 객체 중 myCar 객체를 찾아 가져오기
		Car car3 = (Car) ctx.getBean("myCar");
		car3.drive();

		if (car2 == car3) {
			System.out.println("car2와 car3는 같다.");
		}

		/*
		 * MemberDao type 객체를 bean으로 만들어지도록 설정하고 여기에 MemberDao type 객체의 참조값을 얻어와서
		 * insert() 메서드 호출하기
		 */
		MemberDao dao1 = ctx.getBean(MemberDao.class);
		dao1.insert(new MemberDto());

		MemberDao dao2 = (MemberDao) ctx.getBean("memberDao");
		dao2.insert(new MemberDto());
	}

}
