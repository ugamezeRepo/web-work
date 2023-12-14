package com.example.boot02.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

// @Controller + @ResponseBody
@RestController
public class SendController {

	// "msg" 파라미터가 전달되는 요청 처리하기
	@PostMapping("/send")
	public String send(HttpServletRequest request) { // HttpServletRequest 객체가 필요할 경우 메서드에 선언 시 spring이 참조값 전달

		// 요청 파라미터 추출
		String msg = request.getParameter("msg");
		System.out.println("msg: " + msg);

		// 응답
		return "클라이언트야 메세지 잘 받았어!";
	}

	@PostMapping("/send2")
	public String send2(String msg2) {

		System.out.println("msg: " + msg2);

		return "클라이언트야 메세지 잘 받았어!222";
	}
}
