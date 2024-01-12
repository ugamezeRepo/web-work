package com.example.boot09.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.boot09.dto.FileDto;

@Repository
public class FileDaoImpl implements FileDao {
    @Autowired private SqlSession session;

    @Override
    public void upload(FileDto dto) {
        session.insert("file.upload", dto);
    }
    
    @Override
    public List<FileDto> getList(FileDto dto) {
        
        return session.selectList("file.getList", dto);
    }
    
    @Override
    public int getCount() {
    	return session.selectOne("file.getCount");
    }
    
    @Override
    public FileDto getFile(int num) {
        return session.selectOne("file.getData", num);
    }
}
