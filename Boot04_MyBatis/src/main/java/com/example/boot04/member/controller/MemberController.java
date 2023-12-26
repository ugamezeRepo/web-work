package com.example.boot04.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot04.member.dao.MemberDao;
import com.example.boot04.member.dto.MemberDto;

@Controller
public class MemberController {
	// 의존객체 주입받기(Depenency Injection)
	@Autowired
	MemberDao dao;

	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		/*
		 * 매개변수를 dto로 선언하면 전달되는 파라미터가 자동추출되고 추출된 파라미터가
		 * dto에 자동으로 담겨 전달된다.
		 */
		dao.update(dto);

		return "/member/update";
	}

	// 회원정보 수정 폼 요청처리
	@GetMapping("/member/update_form")
	public String updateForm(int num, Model model) {
		// 파라미터로 전달되는 회원의 번호를 이용해 수정할 회원의 정보를 읽어온다.
		MemberDto dto = dao.getData(num);
		// Model 객체에 수정할 회원의 정보를 담아
		model.addAttribute("dto", dto);

		// tempates/member/update_form.html 페이지를 이용해 응답
		return "member/update_form";
	}

	// 회원정보 삭제 요청처리
	@GetMapping("/member/delete")
	public String delete(int num) { // int type으로 받으면 자동으로 숫자로 바꿔 파라미터를 추출하여 전달
		//MemberDao 객체를 이용해 회원 정보삭제
		dao.delete(num);

		return "redirect:/member/list";
	}

	// 회원정보 저장 요청처리
	@PostMapping("/member/insert")
	public String insert(String name, String addr) { // 전송되는 파라미터의 이름과 동일하게 매개변수를 선언하면 자동 추출
		// DB에 저장할 정보를 MemberDto에 담고
		MemberDto dto = new MemberDto();
		dto.setName(name);
		dto.setAddr(addr);
		// Dao를 이용해 DB에 저장
		dao.insert(dto);

		// 응답
		return "member/insert";
	}

	@GetMapping("/member/insert_form")
	public String insertForm() {

		return "member/insert_form";
	}

	@GetMapping("/member/list")
	public String list(Model model) {
		// 출력해줄 회원목록을 읽어와 모델에 담고
		List<MemberDto> list = dao.getList();
		// Model 객체에 "list"라는 키값으로 회원 목록 담기
		model.addAttribute("list", list);

		// templates/member/list.html Thymeleaf 뷰 엔진에서 회원목록 출력하기
		return "member/list";
	}
}
