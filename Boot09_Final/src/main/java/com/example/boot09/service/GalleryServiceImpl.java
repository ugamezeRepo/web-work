package com.example.boot09.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.boot09.dto.GalleryDto;
import com.example.boot09.exception.NotOwnerException;
import com.example.boot09.repository.GalleryDao;

@Service
public class GalleryServiceImpl implements GalleryService {
    //한 페이지에 몇 개씩 표시할 것인 지
    final int PAGE_ROW_COUNT = 10;
    //하단 페이지를 몇 개씩 표시할 것인 지
    final int PAGE_DISPLAY_COUNT = 5;
    @Autowired private GalleryDao dao;
    @Value("${file.location}") private String fileLocation;

    @Override
    public void addToGallery(GalleryDto dto) {
        // 컨트롤러에서 전달한 GalleryDto에는 String caption과 MultipartFile image 정보만 들어있다.

        // 1. 업로드된 파일 저장
        // 저장할 파일의 이름 겹치지 않는 유일한 문자열로 얻어내기
        String saveFileName = UUID.randomUUID().toString();
        // 저장할 파일의 전체 경로 구성하기
        String filePath = fileLocation + File.separator + saveFileName;

        try {
            // 업로드된 파일을 이동시킬 목적지 File 객체
            File f = new File(filePath);
            // MultipartFile 객체의 메서드를 통해 실제로 이동시키기(전송하기)
            dto.getImage().transferTo(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2. 로그인된 사용자(userName) 읽어오기
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        // 3. GalleryDto에 추가정보를 담고
        dto.setSaveFileName(saveFileName);
        dto.setWriter(userName);
        // 4. DB에 저장하기
        dao.insert(dto);
    }

    @Override
    public void selectOne(Model model, int num) {
        // num에 해당하는 글 정보를 읽어오고
        GalleryDto dto = dao.getData(num);
        // 로그인된 userName을 읽어온다. (로그인 하지 않은 경우 null일 수도 있다.)
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        
        //모델 객체에 담는다.
        model.addAttribute("dto", dto);
        model.addAttribute("userName", userName);
    }

    @Override
    public void selectPage(Model model, int pageNum) {
        //보여줄 페이지의 시작 ROWNUM
        int startRowNum = 1 + (pageNum - 1) * this.PAGE_ROW_COUNT;
        //보여줄 페이지의 끝 ROWNUM
        int endRowNum = pageNum * this.PAGE_ROW_COUNT;

        //startRowNum 과 endRowNum  을 GalleryDto 객체에 담고
        GalleryDto dto = new GalleryDto();
        dto.setStartRowNum(startRowNum);
        dto.setEndRowNum(endRowNum);
        //GalleryDao 객체를 이용해서 회원 목록을 얻어온다.
        List<GalleryDto> list = dao.getList(dto);

        //하단 시작 페이지 번호 
        int startPageNum = 1 + ((pageNum - 1) / this.PAGE_DISPLAY_COUNT) * this.PAGE_DISPLAY_COUNT;
        //하단 끝 페이지 번호
        int endPageNum = startPageNum + this.PAGE_DISPLAY_COUNT - 1;

        //전체 row 의 갯수
        int totalRow = dao.getCount();
        //전체 페이지의 갯수 구하기
        int totalPageCount = (int)Math.ceil(totalRow / (double)this.PAGE_ROW_COUNT);
        //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
        if (endPageNum > totalPageCount) {
            endPageNum = totalPageCount; //보정해 준다. 
        }
        
        model.addAttribute("list", list);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("endPageNum", endPageNum);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("pageNum", pageNum);
    }

    @Override
    public void deleteOne(int num) {
        // 삭제할 글의 정보를 얻어온다.
        GalleryDto dto = dao.getData(num);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        // 로그인된 사용자와 글의 작성자가 같은 지 확인해서 같지 않으면 Exception을 발생시킨다.
        if (!dto.getWriter().equals(userName)) {
            // 예외 발생
            throw new NotOwnerException("이미지를 삭제할 권한이 없습니다");
        }
        // 이미지를 파일 시스템에서 삭제
        String filePath = fileLocation + File.separator + dto.getSaveFileName();
        new File(filePath).delete();
        // DB에서 삭제
        dao.delete(num);
    }
}
