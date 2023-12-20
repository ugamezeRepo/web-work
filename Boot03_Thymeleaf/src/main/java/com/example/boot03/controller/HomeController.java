package com.example.boot03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping({ "/" })
	public String home(Model model, HttpSession session) {
		// Model 객체에 담은 데이터는 request 영역에 담긴다.
		model.addAttribute("fortuneToday", "행운의 빛이 비추니 한번 코인 떡상을 노려봐도 좋아요!");

		// session 영역에 키 "id"에 값 "udada" 담기
		session.setAttribute("id", "udada");

		// 테스트를 위해 Model 객체에 추가 정보 담기
		model.addAttribute("id", "abc111");
		model.addAttribute("amount", 2);

		// 경로 파라미터 테스트를 위한 정보 담기
		model.addAttribute("num", 999);

		// 리턴한 문자열 앞에 /templates/가 붙고, 뒤에 .html이 붙어 /templates/home.html을 가리키게 된다.
		return "home";
	}
}
