package com.example.boot09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.boot09.dto.GalleryDto;
import com.example.boot09.service.GalleryService;

@Controller
public class GalleryController {
    @Autowired private GalleryService service;
    
    @GetMapping("/gallery/delete")
    public String delete(int num) {
        service.deleteOne(num);
        
        return "gallery/delete";
    }
    
    @GetMapping("/gallery/detail")
    public String detail(int num, Model model) {
        service.selectOne(model, num);
        
        return "gallery/detail";
    }

    // 전달되는 pageNum이 있는 지 확인해서 있으면 추출하고 없으면 기본값 1로 설정
    @GetMapping("/gallery/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum, Model model) {
        /*
         *  서비스에 Model 객체와 pageNum을 전달해서
         *  Model에 pageNum에 해당하는 글 목록이 담기도록 한다.
         *  Model에 담긴 내용을 view page(Thymeleaf 템플릿 페이지)에서 사용할 수 있다.
         */
        service.selectPage(model, pageNum);

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
