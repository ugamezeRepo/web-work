package com.example.boot03.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TestController {

	@GetMapping("inc")
	public String inc() {
		return "sub/inc";
	}

	// 가상의 로그인
	@GetMapping("/user/login")
	public String login(HttpSession session) {
		session.setAttribute("id", "udada");
		return "sub/login";
	}

	// 가상의 로그아웃
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		// 세션 초기화
		session.invalidate();
		// 최상위 경로로 리다이렉트
		return "redirect:/";
	}

	@GetMapping("/members")
	public String members(HttpServletRequest request) {
		List<MemberDto> list = new ArrayList<MemberDto>();
		MemberDto dto1 = new MemberDto(1, "송태정", "수서");
		MemberDto dto2 = new MemberDto(2, "김태준", "신림");
		MemberDto dto3 = new MemberDto(3, "김혜란", "수지");
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);

		// Model 객체에 담으면 자동으로 HttpservletRequest 객체에 이런 식으로 담긴다.
		// Spring 프레임워크가 담아준다.
		request.setAttribute("list", list);

		return "sub/members";
	}

	@GetMapping("/notice")
	public String notice(Model model) {
		// DB에서 읽어온 공지사항으로 가정
		List<String> list = new ArrayList<String>();
		list.add("날씨가 추워요!");
		list.add("곧 크리스마스입니다.");
		list.add("어쩌구..");
		list.add("저쩌구...");

		model.addAttribute("list", list);

		return "sub/notice";
	}

	@GetMapping("/member")
	public String member(Model model) {
		// 응답에 필요한 데이터
		MemberDto dto = new MemberDto(1, "김태준", "신림");
		// 응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("dto", dto);

		return "sub/member"; // /templates/sub/member.html
	}

	// GET 방식 /shop/buy?id=xxx&amount=xxx 요청을 처리할 컨트롤러 메서드
	@GetMapping("/shop/buy")
	public String buy(String id, int amount) { // 매개변수명을 파라미터명과 동일하게 선언 시 자동 추출되어 전달
		System.out.printf("id: %s || amount: %d %n", id, amount);

		return "shop/buy"; // /templates/shop/buy.html
	}

	// GET 방식 /sub/pla 요청을 처리할 컨트롤러 메서드
	@GetMapping("/sub/play")
	public String play() {

		return "sub/play"; // /templates/sub/play.html 템플릿을 해석하여 응답
	}

}
