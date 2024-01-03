package com.example.boot05;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {

    return "home";
  }

  @ResponseBody
  @GetMapping("/play")
  public String play() {

    return "play!";
  }

  @GetMapping("/notice")
  public String notice(Model model) {
    List<String> list = new ArrayList<String>();
    list.add("2023년이 얼마 남기 않았네요.");
    list.add("어쩌구...");
    list.add("저쩌구...");
    // 공지사항을 "noticeList"라는 키값으로 담고
    model.addAttribute("noticeList", list);
    // templates/notice.html 페이지로 forward 이동 후 응답
    return "notice";
  }

  @ResponseBody
  @GetMapping("/study")
  public String study() {

    return "let's study!";
  }
}
