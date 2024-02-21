package com.example.boot11.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/notice")
    public List<String> notice() {
        List<String> list = new ArrayList<>();
        list.add("프로젝트 기간입니다.");
        list.add("힘내서 열심히 공부합니다.");
        list.add("어쩌구...");
        list.add("저쩌구...");
        
        return list;
    }
}
