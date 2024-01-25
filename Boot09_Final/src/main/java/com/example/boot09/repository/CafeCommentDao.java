package com.example.boot09.repository;

import java.util.List;
import com.example.boot09.dto.CafeCommentDto;

public interface CafeCommentDao {    
    // 댓글 추가
    public void insert(CafeCommentDto dto);
    
    // 추가할 댓글의 글번호 리턴 메서드
    public int getSequence();
    
    // 원글에 달린 댓글의 개수 얻어내기
    public int getCount(int ref_group);
    
    // 댓글 하나의 정보 리턴
    public CafeCommentDto getData(int num);    
    
    // 댓글 목록
    public List<CafeCommentDto> getList(CafeCommentDto dto); 
    
    // 댓글 수정
    public void update(CafeCommentDto dto);
    
    // 댓글 삭제
    public void delete(int num);
    
}
