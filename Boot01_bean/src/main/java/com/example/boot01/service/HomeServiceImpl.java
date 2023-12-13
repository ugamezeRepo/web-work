package com.example.boot01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// spring이 이 클래스로 객체를 생성해 bean으로 관리하도록 한다.
@Component
public class HomeServiceImpl implements HomeService {
	// 필드로 필요한 type 선언 후 @Autowired라는 어노테이션을 붙여 해당 객체 주입
	@Autowired
	private Drill drill;

	@Override
	public void clean(String name) {
		System.out.println(name + "의 집 청소하기");
	}

	@Override
	public void wash(String name) {
		System.out.println(name + "의 집 빨래하기");
	}

	@Override
	public void hole(String name) {
		System.out.println(name + " 구멍 뚫기");
		// 구멍을 뚫기 위해 Drill type이 필요
		// spring이 관리하는 bean을 이용해 구멍을 뚫어야한다.
		drill.on();
	}
}
