package com.example.boot09.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.boot09.dto.TeacherCafeDto;
import com.example.boot09.exception.NotOwnerException;
import com.example.boot09.repository.TeacherCafeDao;

@Service
public class TeacherCafeServiceImpl implements TeacherCafeService {
    @Autowired private TeacherCafeDao tcafeDao;
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
        int totalRow = tcafeDao.getCount(dto);
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
        List<TeacherCafeDto> list = tcafeDao.getList(dto);
        
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
        
    }
    
    @Override
    public void getDetail(Model model, int num) {
        // 글번호를 이용해서 글 하나의 정보를 얻어와서 
        TeacherCafeDto dto = tcafeDao.getData(num);
        // userName 도 읽어와서 담아준다(로그인 되지 않았다면 null 이다)
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // Model 객체에 담아준다.
        model.addAttribute("dto", dto);
        model.addAttribute("userName", userName);
    }
    
    @Override
    public void deleteContent(int num) {
        // 글 작성자와
        String writer = tcafeDao.getData(num).getWriter();
        // 로그인된 사용자와 같은 경우에만 삭제
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!writer.equals(userName)) {
            throw new NotOwnerException("글 작성자와 일치하지 않습니다.");
            // DB에서 num에 해당하는 글 삭제하기
        }
        // DB에서 num에 해당하는 글 삭제하기
        tcafeDao.delete(num);
    }
    
    @Override
    public void getData(Model model, int num) {
        TeacherCafeDto dto = tcafeDao.getData(num);
        model.addAttribute("dto", dto);
        
    }
    
    @Override
    public void updateContent(TeacherCafeDto dto) {
        tcafeDao.update(dto);
    }
    
}
