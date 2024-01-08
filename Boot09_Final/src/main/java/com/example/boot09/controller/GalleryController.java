package com.example.boot09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.boot09.dto.GalleryDto;
import com.example.boot09.service.GalleryService;

@Controller
public class GalleryController {
    @Autowired
    private GalleryService service;

    @GetMapping("/gallery/list")
    public String list() {
        
        return "gallery/list";
    }
    
    @GetMapping("/gallery/upload_form")
    public String uploadForm() {
        
        return "gallery/upload_form";
    }
    
    @PostMapping("/gallery/upload")
    public String upload(GalleryDto dto) {
        // caption과 image가 들어있는 GalleryDto를 서비스에 전달해서 저장한다.
        service.addToGallery(dto);

        // 목록 보기로 임시 리다이렉트
        return "redirect:/gallery/list";
    }
}
