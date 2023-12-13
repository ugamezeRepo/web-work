package com.example.boot01.service;

import org.springframework.stereotype.Component;

@Component
public class DrillImpl implements Drill {

	@Override
	public void on() {
		System.out.println("뚫어보자~!");
	}

	@Override
	public void off() {
		System.out.println("이제 좀 쉬자...");
	}

}
