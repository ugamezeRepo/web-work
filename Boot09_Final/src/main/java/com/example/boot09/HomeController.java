package com.example.boot09;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // DB에서 읽어온 공지사항으로 가정
        List<String> notice = Arrays.asList("매우 맑음", "오늘 만나는 사람에게 잘하세요", "오늘은 득근득근한 날이에요");
        // view page에 전달
        model.addAttribute("notice", notice);

        return "home";
    }
}
