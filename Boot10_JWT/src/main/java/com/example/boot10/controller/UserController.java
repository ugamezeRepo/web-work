package com.example.boot10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.boot10.dto.UserDto;
import com.example.boot10.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    // util 역할을 하는 서비스 객체를 인터페이스 type으로 DI 받아서 사용한다 
    @Autowired
    private UserService service;

    @PostMapping("/user/pwd_update")
    public String pwdUpdate(UserDto dto, HttpSession session) {
        service.updatePassword(dto);
        session.invalidate();

        return "user/pwd_update";
    }

    @GetMapping("/user/pwd_updateform")
    public String pwdUpdateForm(Model model) {

        return "user/pwd_updateform";
    }

    @PostMapping("/user/update")
    public String update(UserDto dto) {
        service.updateUser(dto);

        // 개인정보 보기로 리다이렉트
        return "redirect:/user/info";
    }

    @GetMapping("/user/updateform")
    public String updateform(Model model) {
        service.getInfo(model);

        return "user/updateform";
    }

    @GetMapping("/user/info")
    public String info(Model model) {
        service.getInfo(model);

        return "user/info";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String signup(UserDto dto) {
        // 서비스(util) 객체를 이용해서 가입정보를 등록한다.
        service.addUser(dto);

        return "user/signup";
    }

    // 회원가입 폼 요청처리
    @GetMapping("/user/signup_form")
    public String signupForm() {

        return "user/signup_form";
    }

    // 세션 허용 개수 초과 시 
    @GetMapping("/user/expired")
    public String userExpired() {

        return "user/expired";
    }

    // 권한 부족 시 or 403 인 경우 
    @GetMapping("/user/denied")
    public String userDenied() {

        return "user/denied";
    }

    // 로그인이 필요한 요청 경로를 로그인하지 않은 상태로 요청하면 리다이렉트되는 요청 경로 
    @GetMapping("/user/required_loginform")
    public String required_loginform() {

        return "user/required_loginform";
    }

    // 로그인 폼을 제출(post) 한 로그인 프로세스 중에 forward되는 경로이기 때문에 @PostMapping임에 주의!
    @PostMapping("/user/login_fail")
    public String loginFail() {

        //로그인 실패임을 알릴 페이지
        return "user/login_fail";
    }

    @RequestMapping("/user/loginform")
    public String loginform() {

        // templates/user/loginform.html 페이지로 forward 이동해서 응답 
        return "user/loginform";
    }

    @PostMapping("/user/login_success")
    public String loginSuccess() {

        return "user/login_success";
    }
}


