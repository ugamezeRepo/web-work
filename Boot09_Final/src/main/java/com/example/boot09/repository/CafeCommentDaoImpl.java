package com.example.boot09.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.boot09.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImpl implements CafeCommentDao {
    @Autowired SqlSession session;
    
    @Override
    public int getSequence() {
        
        return session.selectOne("cafeComment.getSequence");
    }
    
    @Override
    public void insert(CafeCommentDto dto) {
        session.insert("cafeComment.insert", dto);
    }
    
    @Override
    public List<CafeCommentDto> getList(CafeCommentDto dto) {
        
        return session.selectList("cafeComment.getList", dto);
    }
}
