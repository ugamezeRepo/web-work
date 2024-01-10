package com.example.boot09.service;

import org.springframework.ui.Model;
import com.example.boot09.dto.TeacherCafeDto;

public interface TeacherCafeService {
    public void getList(Model model, int pageNum);
    
    public void saveContent(TeacherCafeDto dto);
    
    public void getDetail(Model model, int num); // 글 상세보기 관련 기능
    
    public void deleteContent(int num);
    
    public void getData(Model model, int num); // 글 수정 폼 출력 관련 기능
    
    public void updateContent(TeacherCafeDto dto);

}
