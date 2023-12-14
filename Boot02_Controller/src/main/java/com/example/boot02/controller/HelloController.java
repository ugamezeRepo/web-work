package com.example.boot02.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/* 클라이언트의 요청을 처리한 컨트롤러를 정의하고 bean으로 만들기 */

@Controller // 1. bean으로 만들기 + 2. 요청을 처리하는 컨트롤러 역할하기
public class HelloController {

	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		return "Nice to meet you!";
	}

	@ResponseBody
	@GetMapping("/fortune")
	public String fortune() {
		return "따뜻한 커피로 손을 녹이고 따뜻한 한마디로 마음을 녹여보세요.";
	}

	@ResponseBody
	@GetMapping("/member")
	public Map<String, Object> member() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", 1);
		map.put("name", "udada");
		map.put("isMan", true);

		return map;
	}

	@ResponseBody
	@GetMapping("/friends")
	public List<String> friends() {
		List<String> names = new ArrayList<String>();
		names.add("송태정");
		names.add("김태준");
		names.add("김혜란");
		names.add("오영찬");
		names.add("이승우");

		return names;
	}

	@ResponseBody
	@GetMapping("/members")
	public List<Map<String, Object>> members() {
		Map<String, Object> mem1 = new HashMap<String, Object>();
		mem1.put("num", 1);
		mem1.put("name", "송태정");
		mem1.put("isMan", true);
		Map<String, Object> mem2 = new HashMap<String, Object>();
		mem2.put("num", 2);
		mem2.put("name", "김태준");
		mem2.put("isMan", true);
		Map<String, Object> mem3 = new HashMap<String, Object>();
		mem3.put("num", 3);
		mem3.put("name", "김혜란");
		mem3.put("isMan", false);

		List<Map<String, Object>> members = new ArrayList<>();
		members.add(mem1);
		members.add(mem2);
		members.add(mem3);

		return members;
	}

}
