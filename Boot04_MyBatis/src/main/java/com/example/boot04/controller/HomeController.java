package com.example.boot04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		List<String> list = new ArrayList<>();
		list.add("추운 겨울이네요..");
		list.add("감기 조심하세요!");
		list.add("어쩌구...");
		list.add("저쩌구...");

		model.addAttribute("noticeList", list);
		// templates/home.html 페이지를 해석하여 응답

		return "home";
	}
}
