package com.example.boot09.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.boot09.dto.GalleryDto;

@Repository // dao가 bean이 되도독 어노테이션 붙이기
public class GalleryDaoImpl implements GalleryDao {
    @Autowired // 핵심 의존 객체 DI
    private SqlSession session;

    @Override
    public void insert(GalleryDto dto) {
        session.insert("gallery.insert", dto);
    }

    @Override
    public GalleryDto getData(int num) {

        return session.selectOne("gallery.getData", num);
    }

    @Override
    public int getCount() {

        return session.selectOne("gallery.getCount");
    }

    @Override
    public List<GalleryDto> getList(GalleryDto dto) {

        return session.selectList("gallery.getList", dto);
    }

    @Override
    public void delete(int num) {
        session.delete("gallery.delete", num);
    }
}
