package com.example.boot09.service;

import org.springframework.ui.Model;
import com.example.boot09.dto.FileDto;

public interface FileService {
    public void upload(FileDto dto);
    
    public void selectFileAll(Model model, FileDto dto);
    
    public void selectFile(Model model, int num);
}
