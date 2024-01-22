package com.example.boot09.repository;

import java.util.List;
import com.example.boot09.dto.CafeCommentDto;

public interface CafeCommentDao {
    // 추가할 댓글의 글번호 리턴 메서드
    public int getSequence();
    // 댓글 추가
    public void insert(CafeCommentDto dto);

    public List<CafeCommentDto> getList(CafeCommentDto dto); 
}
