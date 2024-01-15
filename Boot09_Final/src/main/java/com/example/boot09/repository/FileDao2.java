package com.example.boot09.repository;

import java.util.List;
import com.example.boot09.dto.FileDto2;

public interface FileDao2 {
    public void insert(FileDto2 dto);
    
    public FileDto2 getData(int num);
    
    public List<FileDto2> getList(FileDto2 dto);
    
    public int getCount(FileDto2 dto);
    
    public void delete(int num);

}
