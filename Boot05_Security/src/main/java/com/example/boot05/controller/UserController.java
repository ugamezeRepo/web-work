package com.example.boot05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  @GetMapping("/user/loginform")
  public String loginform() {

    // templates/user/loginform.html 페이지로 forward 이동해 응답
    return "user/loginform";
  }

  @PostMapping("/user/login_success")
  public String loginSuccess() {

    return "user/login_success";
  }

  // 로그인 폼을 제출(post)한 로그인 프로세스 중 forward되는 경로이므로 @PostMapping
  @PostMapping("/user/login_fail")
  public String loginFail() {

    return "user/login_fail";
  }
}
