package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.boot11.dto.UserDto;
import com.example.boot11.util.JwtUtil;

@RestController
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/auth")
    public String auth(@RequestBody UserDto dto) throws Exception {
        try {
            // 입력한 username과 password를 인증 토큰 객체에 담아
            UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
            // 인증 매니저 객체를 이용해 인증 진행
            authManager.authenticate(authToken);
        } catch (Exception e) {
            // 예외가 발생하면 인증 실패(아이디 혹은 비밀번호 틀림 등..)
            e.printStackTrace();
            throw new Exception("아이디 혹은 비밀번호가 틀립니다.");
        }
        
        // 예외 발새 없이 실행된다면 인증 통과. 토큰을 발급해 응답
        String token = jwtUtil.generateToken(dto.getUserName());
        
        return token;
    }
    
    @GetMapping("/ping")
    public String ping() {
        
        return "pong";
    }
}
