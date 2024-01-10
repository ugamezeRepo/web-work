package com.example.boot09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.boot09.dto.TeacherCafeDto;
import com.example.boot09.service.TeacherCafeService;

@Controller
public class TeacherCafeController {
    @Autowired private TeacherCafeService service;
    
    @GetMapping("/teacher/cafe/updateform")
    public String updateform(Model model, int num) {
        service.getData(model, num);
        
        return "teacher/cafe/updateform";
    }
    
    @PostMapping("/teacher/cafe/update")
    public String update(TeacherCafeDto dto) {
        service.updateContent(dto);
        
        return "redirect:/teacher/cafe/detail?num=" + dto.getNum();
    }
    
    @GetMapping("/teacher/cafe/detail")
    public String detail(Model model, int num) {
        
        service.getDetail(model, num);
        
        return "teacher/cafe/detail";
    }
    
    @PostMapping("/teacher/cafe/insert")
    public String insert(TeacherCafeDto dto) {
        //서비스를 이용해서 새글을 저장한다
        service.saveContent(dto);
        
        return "teacher/cafe/insert";
    }
    
    @GetMapping("/teacher/cafe/insertform")
    public String insertform() {
        
        return "teacher/cafe/insertform";
    }

    @GetMapping("/teacher/cafe/list")
    public String list(Model model, @RequestParam(defaultValue = "1") int pageNum) {
        service.getList(model, pageNum);
        
        return "teacher/cafe/list";
    }
    
}
