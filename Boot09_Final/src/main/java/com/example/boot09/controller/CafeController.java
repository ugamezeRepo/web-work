package com.example.boot09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.boot09.dto.CafeDto;
import com.example.boot09.service.CafeService;

@Controller
public class CafeController {
    @Autowired private CafeService service;
    
    @GetMapping("/cafe/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum, Model model) {
        service.selectPage(model, pageNum);
        
        return "cafe/list";
    }
    
    @GetMapping("cafe/detail")
    public String detail(int num, Model model) {
        service.selectOne(model, num);
        
        return "cafe/detail";
    }
    
    @GetMapping("/cafe/insert_form")
    public String uploadForm() {
        
        return "cafe/insert_form";
    }
    
    @PostMapping("/cafe/insert")
    public String insert(CafeDto dto) {
        service.addToCafe(dto);
        
        return "redirect:/cafe/list";
    }
}
