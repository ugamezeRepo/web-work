package com.example.boot09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.boot09.dto.FileDto2;
import com.example.boot09.service.FileService2;


@Controller
public class FileController2 {
    @Autowired private FileService2 service;

    @GetMapping("/teacher/file/list")
    public String list(Model model, FileDto2 dto) {
    	service.getList(model, dto);
        
        return "teacher/file/list";
    }
    
    
    @GetMapping("/teacher/file/download")
    public ResponseEntity<InputStreamResource> download(int num) {
        
        return null;
    }
    
    @GetMapping("/teacher/file/upload_form")
    public String uploadForm() {
        
        return "teacher/file/upload_form";
    }
    
    @PostMapping("/teacher/file/upload")
    public String upload(FileDto2 dto) {
        service.saveFile(dto);
        
        return "teacher/file/upload";
    }
    
}
