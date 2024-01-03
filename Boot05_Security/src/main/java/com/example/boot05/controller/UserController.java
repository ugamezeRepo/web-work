package com.example.boot05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  // 세션 허용 개수 초과 시
  @GetMapping("/user/expired")
  public String userExpired() {

    return "user/expired";
  }

  // 권한 부족 시 or 403 Forbidden인 경우
  @GetMapping("/user/denied")
  public String userDenied() {

    return "user/denied";
  }

  // ROLL_STAFF, ROLL_ADMIN만 요청 가능
  @GetMapping("/staff/user/list")
  public String userList() {

    return "user/list";
  }

  // ROLL_ADMIN만 요청 가능
  @GetMapping("/admin/user/manage")
  public String userManage() {

    return "user/manage";
  }

  // 로그인이 필요한 요청경로를 로그인하지 않은 상태로 요청하면 리다이렉트 되는 요청경로
  @GetMapping("/user/required_loginform")
  public String required_loginform() {

    return "user/required_loginform";
  }

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
