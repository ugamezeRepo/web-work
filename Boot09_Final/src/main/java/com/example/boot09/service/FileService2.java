package com.example.boot09.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.boot09.dto.FileDto2;

public interface FileService2 {
    public void saveFile(FileDto2 dto);
    
    public void getList(Model model, FileDto2 dto);
    
    public void getDetail(Model model, int num);
    
    // 다운로드할 파일 하나의 정보 읽어오기
    public ResponseEntity<InputStreamResource> getFileData(int num);
    
    public void updateFile(Model model, int num);
    
    // 파일 삭제
    public void deleteFile(int num);
    
}
