package com.example.boot09.repository;

import java.util.List;
import com.example.boot09.dto.TeacherCafeDto;

public interface TeacherCafeDao {
    public List<TeacherCafeDto> getList(TeacherCafeDto dto);

    public int getCount();
    
    public void insert(TeacherCafeDto dto);
    
    public TeacherCafeDto getData(int num);
    
    public void delete(int num); 
    
    public void update(TeacherCafeDto dto);

}
