package com.example.boot09.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.boot09.dto.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao {
    @Autowired private SqlSession session;

    @Override
    public void insert(CafeDto dto) {
        session.insert("cafe.insert", dto);
        
    }

    @Override
    public CafeDto getData(int num) {

        return session.selectOne("cafe.getData", num);
    }

    @Override
    public int getCount() {
        
        return session.selectOne("cafe.getCount");
    }

    @Override
    public List<CafeDto> getList(CafeDto dto) {

        return session.selectList("cafe.getList", dto);
    }

    @Override
    public void update(CafeDto dto) {
        session.update("cafe.update", dto);
    }

    @Override
    public void updateView(CafeDto dto) {
        session.update("cafe.updateView", dto);
    }

    @Override
    public void delete(int num) {
        session.delete("cafe.delete", num);
    }
    
}
