package com.example.boot09.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.boot09.dto.CafeCommentDto;
import com.example.boot09.dto.TeacherCafeDto;
import com.example.boot09.exception.NotOwnerException;
import com.example.boot09.repository.CafeCommentDao;
import com.example.boot09.repository.TeacherCafeDao;

@Service
public class TeacherCafeServiceImpl implements TeacherCafeService {
    @Autowired private TeacherCafeDao dao;
    @Autowired CafeCommentDao commentDao;
    // 글 페이징 처리
    //한 페이지에 글을 몇개씩 표시할 것인지
    final int PAGE_ROW_COUNT = 5;
    //하단 페이지 UI를 몇개씩 표시할 것인지
    final int PAGE_DISPLAY_COUNT = 5;
    
    @Override
    public void getList(Model model, TeacherCafeDto dto) {
        int pageNum = dto.getPageNum();
        //보여줄 페이지의 시작 ROWNUM
        int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
        //보여줄 페이지의 끝 ROWNUM
        int endRowNum = pageNum * PAGE_ROW_COUNT;
        //하단 시작 페이지 번호 
        int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
        //하단 끝 페이지 번호
        int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
        //전체 글의 갯수
        int totalRow = dao.getCount(dto);
        //전체 페이지의 갯수 구하기
        int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
        //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
        if (endPageNum > totalPageCount) {
            endPageNum = totalPageCount; //보정해 준다. 
        }

        //위에서 계산된 startRowNum 과 endRowNum 을 담고
        dto.setStartRowNum(startRowNum);
        dto.setEndRowNum(endRowNum);
        //TeacherCafeDto 를 인자로 전달해서 글목록 얻어오기
        List<TeacherCafeDto> list = dao.getList(dto);
        
        model.addAttribute("list", list);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("endPageNum", endPageNum);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("dto", dto); // 키워드 정보가 들어있는 dto 모델에 담기
        model.addAttribute("totalRow", totalRow);
    }
    
    @Override
    public void saveContent(TeacherCafeDto dto) {
        // 글 작성자를 dto에 담아서
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        dto.setWriter(userName);
        // DB에 저장
        dao.insert(dto);
        
    }
    
    @Override
    public void getDetail(Model model, TeacherCafeDto dto) {
        // 글번호를 이용해서 글 하나의 정보를 얻어와서 
        TeacherCafeDto resultDto = dao.getDetail(dto);
        // 원래의 검색 조건을 글 정보가 들어있는 resultDto에 추가
        resultDto.setCondition(dto.getCondition());
        resultDto.setKeyword(dto.getKeyword());
        // userName 도 읽어와서 담아준다(로그인 되지 않았다면 null 이다)
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // 댓글 목록을 얻어온다.
        // CafeDto에 ref_group 번호를 담아 dao에 전달하고 댓글 목록을 얻어낸다.
        CafeCommentDto commentDto = new CafeCommentDto();
        // 원글의 글번호를 담아서
        commentDto.setRef_group(dto.getNum());
        
        // 요청된 댓글의 페이지 번호
        int pageNum = 1;
        
        // [ 댓글 페이징 처리 관련 로직 ]
        // 한 페이지에 댓글을 몇 개씩 표시할 것인 지
        final int PAGE_ROW_COUNT = 10;
        
        // 보여줄 페이지의 시작 ROWNUM
        int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
        // 보여줄 페이지의 끝 ROWNUM
        int endRowNum = pageNum * PAGE_ROW_COUNT;
        // 계산된 값을 dto에 담는다.
        commentDto.setStartRowNum(startRowNum);
        commentDto.setEndRowNum(endRowNum);
        
        // 원글에 달린 댓글 목록 얻어내기
        List<CafeCommentDto> commentList = commentDao.getList(commentDto);
        
        // Model 객체에 담아준다.
        model.addAttribute("dto", resultDto);
        model.addAttribute("userName", userName);
        model.addAttribute("commentList", commentList);
    }
    
    @Override
    public void deleteContent(int num) {
        // 글 작성자와
        String writer = dao.getData(num).getWriter();
        // 로그인된 사용자와 같은 경우에만 삭제
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!writer.equals(userName)) {
            throw new NotOwnerException("글 작성자와 일치하지 않습니다.");
            // DB에서 num에 해당하는 글 삭제하기
        }
        // DB에서 num에 해당하는 글 삭제하기
        dao.delete(num);
    }
    
    @Override
    public void getData(Model model, int num) {
        TeacherCafeDto dto = dao.getData(num);
        model.addAttribute("dto", dto);
    }
    
    @Override
    public void updateContent(TeacherCafeDto dto) {
        dao.update(dto);
    }
    
    @Override
    public void saveComment(CafeCommentDto dto) {
        // 댓글 작성자를 SpringSecurity로부터 얻어내기
        String writer = SecurityContextHolder.getContext().getAuthentication().getName();
        // 글번호를 미리 얻어내기
        int num = commentDao.getSequence();
        dto.setWriter(writer);
        dto.setNum(num);
        // 만일 comment_group 번호가 넘어오지 않으면 원글의 댓글이다
        if(dto.getComment_group() == 0) {
            // 원글의 댓글인 경우 댓글의 번호(num)가 곧 comment_group 번호
            dto.setComment_group(num);
        }
        // DB에 저장
        commentDao.insert(dto);
    }
    
    @Override
    public void updateComment(CafeCommentDto dto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String writer = commentDao.getData(dto.getNum()).getWriter();
        // String writer = dto.getTarget_id();
        
        if(!userName.equals(writer)) {
            throw new NotOwnerException("댓글의 작성자가 아닙니다.");
        }
        
        commentDao.update(dto);        
    }
    
    @Override
    public void deleteComment(int num) {
        // 로그인된 사용자와 댓글 작성자가 같은 지 확인
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String writer = commentDao.getData(num).getWriter();
        
        if(!userName.equals(writer)) {
            throw new NotOwnerException("댓글의 작성자가 아닙니다.");
        }
        // 삭제 작업을 한다.
        commentDao.delete(num);
    }
    
    @Override
    public void getCommentList(Model model, CafeCommentDto dto) {
        // 요청된 댓글의 페이지 번호
        int pageNum = dto.getPageNum();
        
        // [ 댓글 페이징 처리 관련 로직 ]
        // 한 페이지에 댓글을 몇 개씩 표시할 것인 지
        final int PAGE_ROW_COUNT = 10;
        
        // 보여줄 페이지의 시작 ROWNUM
        int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
        // 보여줄 페이지의 끝 ROWNUM
        int endRowNum = pageNum * PAGE_ROW_COUNT;
        // 계산된 값을 dto에 담는다.
        dto.setStartRowNum(startRowNum);
        dto.setEndRowNum(endRowNum);
        
        // pageNum에 해당하는 페이지 목록만 select 되도록 한다.
        List<CafeCommentDto> commentList = commentDao.getList(dto);
        
        // 응답에 필요한 데이터를 Model 객체에 담는다.
        model.addAttribute("commentList", commentList);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("ref_group", dto.getRef_group());
    }
}
 