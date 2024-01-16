package com.example.boot09.repository;

import java.util.List;
import com.example.boot09.dto.TeacherCafeDto;

public interface TeacherCafeDao {
    public List<TeacherCafeDto> getList(TeacherCafeDto dto);

    public int getCount(TeacherCafeDto dto);
    
    public void insert(TeacherCafeDto dto);
    
    public TeacherCafeDto getData(int num);
    
    public TeacherCafeDto getDetail(TeacherCafeDto dto);
    
    public void delete(int num); 
    
    public void update(TeacherCafeDto dto);

}
