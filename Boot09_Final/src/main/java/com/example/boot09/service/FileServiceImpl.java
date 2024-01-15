package com.example.boot09.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot09.dto.FileDto;
import com.example.boot09.repository.FileDao;

@Service
public class FileServiceImpl implements FileService{
    @Autowired FileDao dao;
    @Value("${file.location}") private String fileLocation;
    final int PAGE_ROW_COUNT = 3;
    final int PAGE_DISPLAY_COUNT = 5;
    
    @Override
    public void upload(FileDto dto) {
    	MultipartFile myFile = dto.getMyFile();
        String saveFileName = UUID.randomUUID().toString();
        String filePath = fileLocation + File.separator + saveFileName;
        
        try {
            File f = new File(filePath);
            dto.getMyFile().transferTo(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        dto.setWriter(userName);
        dto.setOrgFileName(myFile.getOriginalFilename());
        dto.setSaveFileName(saveFileName);
        dto.setFileSize(myFile.getSize());
        dao.upload(dto);
    }
    
    @Override
    public void selectFileAll(Model model, FileDto dto) {
    	int pageNum = dto.getPageNum();
    	int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
    	int endRowNum = pageNum * PAGE_ROW_COUNT;
    	int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
    	int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
    	int totalRow = dao.getCount();
        int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
        if (endPageNum > totalPageCount) {
            endPageNum = totalPageCount;
        }

        dto.setStartRowNum(startRowNum);
        dto.setEndRowNum(endRowNum);
        List<FileDto> list = dao.getList(dto);
        
        model.addAttribute("list", list);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("endPageNum", endPageNum);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("dto", dto);
        model.addAttribute("totalRow", totalRow);
        model.addAttribute("pageNum", pageNum);
    }
    
    @Override
    public void selectFile(Model model, int num) {
        FileDto dto = dao.getFile(num);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        
        model.addAttribute("dto", dto);
        model.addAttribute("userName", userName);
    }
    
}
