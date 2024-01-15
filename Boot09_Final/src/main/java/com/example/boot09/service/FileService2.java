package com.example.boot09.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.boot09.dto.FileDto2;

public interface FileService2 {
    public void saveFile(FileDto2 dto);
    
    public void getList(Model model, FileDto2 dto);
    
    public void getDetail(Model model, int num);
    
    public ResponseEntity<InputStreamResource> getFileData(int num);
    
    public void updateFile(Model model, int num);
    
    public void deleteFile(int num);
}
