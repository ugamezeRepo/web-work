package com.example.boot09.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.boot09.dto.FileDto2;

@Repository
public class FileDaoImpl2 implements FileDao2 {
    @Autowired private SqlSession session;

    @Override
    public void insert(FileDto2 dto) {
        session.insert("file2.insert", dto);
    }
    
    @Override
    public FileDto2 getData(int num) {
        
        return session.selectOne("file2.getData", num);
    }

    @Override
    public List<FileDto2> getList(FileDto2 dto) {
        
        return session.selectList("file2.getList", dto);
    }
    
    @Override
    public int getCount(FileDto2 dto) {
        
    	return session.selectOne("file2.getCount", dto);
    }
    
    @Override
    public void delete(int num) {
        session.delete("file2.delete", num);        
    }
    
}
