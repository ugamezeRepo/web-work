package com.example.boot09.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.boot09.dto.CafeDto;
import com.example.boot09.dto.TeacherCafeDto;

@Repository
public class TeacherCafeDaoImpl implements TeacherCafeDao{
    // mybatis 설정이 되어있다면 Sqlsession 객체는 정상적으로 spring bean container에서 관리된다.
    @Autowired private SqlSession session;

    @Override
    public List<TeacherCafeDto> getList(TeacherCafeDto dto) {
        /*
         *  mapper's namespace => tcafe
         *  sql's id => getList
         *  parameterType => tcafeDto
         *  resultType => tcafeDto
         *  select된 row의 개수는 여러 개일 가능성이 있다.
         */
        List<TeacherCafeDto> list = session.selectList("tcafe.getList", dto);
        
        return list;
    }
    
    @Override
    public int getCount() {
        
        // 전체 글의 개수 리턴
        return session.selectOne("tcafe.getCount");
    }

    @Override
    public void insert(TeacherCafeDto dto) {
        session.insert("tcafe.insert", dto);
    }

    @Override
    public TeacherCafeDto getData(int num) {
        
        return session.selectOne("tcafe.getData", num);
    }
    
    @Override
    public void delete(int num) {
        session.delete("tcafe.delete", num);
    }
    
    @Override
    public void update(TeacherCafeDto dto) {
        session.update("tcafe.update", dto);
    }
    
}
