package com.example.boot09.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.boot09.dto.CafeDto;
import com.example.boot09.exception.NotOwnerException;
import com.example.boot09.repository.CafeDao;

@Service
public class CafeServiceImpl implements CafeService {
    final int PAGE_ROW_COUNT = 5;
    final int PAGE_DISPLAY_COUNT = 5;
    @Autowired private CafeDao dao;

    @Override
    public void addToCafe(CafeDto dto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        dto.setWriter(userName);
        dao.insert(dto);
    }

    @Override
    public void selectOne(Model model, int num) {
        CafeDto dto = dao.getData(num);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        
        model.addAttribute("dto", dto);
        model.addAttribute("userName", userName);
    }

    @Override
    public void selectPage(Model model, int pageNum) {
        int startRowNum = 1 + (pageNum - 1) * this.PAGE_ROW_COUNT;
        int endRowNum = pageNum * this.PAGE_ROW_COUNT;
        int startPageNum = 1 + ((pageNum - 1) / this.PAGE_DISPLAY_COUNT) * this.PAGE_DISPLAY_COUNT;
        int endPageNum = startPageNum + this.PAGE_DISPLAY_COUNT - 1;
        int totalRow = dao.getCount();
        int totalPageCount = (int)Math.ceil(totalRow / (double)this.PAGE_ROW_COUNT);
        if (endPageNum > totalPageCount) {
            endPageNum = totalPageCount; 
        }

        CafeDto dto = new CafeDto();
        dto.setStartRowNum(startRowNum);
        dto.setEndRowNum(endRowNum);
        List<CafeDto> list = dao.getList(dto);
        
        model.addAttribute("list", list);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("endPageNum", endPageNum);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("pageNum", pageNum);
    }
    
    @Override
    public void updateOne(CafeDto dto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        
        if (!dto.getWriter().equals(userName)) {
            throw new NotOwnerException("카페글을 삭제할 권한이 없습니다");
        }
        dao.update(dto);
    }

    @Override
    public void deleteOne(int num) {
        CafeDto dto = dao.getData(num);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        
        if (!dto.getWriter().equals(userName)) {
            throw new NotOwnerException("카페글을 삭제할 권한이 없습니다");
        }
        dao.delete(num);
    }
    
    
}
