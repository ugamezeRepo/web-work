package com.example.boot09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.boot09.dto.FileDto;
import com.example.boot09.service.FileService;


@Controller
public class FileController {
    @Autowired private FileService service;

    @GetMapping("/file/list")
    public String list(Model model, FileDto dto) {
    	service.selectFileAll(model, dto);
        
        return "file/list";
    }
    
    
    @GetMapping("/file/download")
    public String download(Model model, int num) {
        service.selectFile(model, num);
        
        return "file/download";
    }
    
    @GetMapping("/file/upload_form")
    public String uploadForm() {
        
        return "file/upload_form";
    }
    
    @PostMapping("/file/upload")
    public String upload(FileDto dto) {
        service.upload(dto);
        
        return "redirect:/file/list";
    }
    
}
