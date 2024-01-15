package com.example.boot09.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot09.dto.FileDto2;
import com.example.boot09.repository.FileDao2;

@Service
public class FileServiceImpl2 implements FileService2{
    @Autowired FileDao2 dao;
    @Value("${file.location}") private String fileLocation;
    final int PAGE_ROW_COUNT = 10;
    final int PAGE_DISPLAY_COUNT = 5;
    
    @Override
    public void saveFile(FileDto2 dto) {
        // 파일 업로드 처리를 위한 객체의 참조값 얻어오기 (업로드된 파일에 대한 정보를 얻어낼 객체)
    	MultipartFile myFile = dto.getMyFile();
    	// 원본 파일명
    	String originalFilename = myFile.getOriginalFilename();
    	// 파일의 크기
    	long fileSize = myFile.getSize();
    	// 저장할 파일명을 하나 얻어낸다.
        String saveFileName = UUID.randomUUID().toString();
        // 저장할 파일의 상세경로
        String filePath = fileLocation + File.separator + saveFileName;
        try {
            // File 객체 생성
            File f = new File(filePath);
            // 파일을 원하는 곳에 저장하기
            myFile.transferTo(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        dto.setWriter(userName);
        dto.setOrgFileName(originalFilename);
        dto.setSaveFileName(saveFileName);
        dto.setFileSize(fileSize);
        dao.insert(dto);
    }
    
    @Override
    public void getList(Model model, FileDto2 dto) {
    	int pageNum = dto.getPageNum();
    	int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
    	int endRowNum = pageNum * PAGE_ROW_COUNT;
    	int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
    	int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
    	int totalRow = dao.getCount(dto);
        int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
        if (endPageNum > totalPageCount) {
            endPageNum = totalPageCount;
        }

        dto.setStartRowNum(startRowNum);
        dto.setEndRowNum(endRowNum);
        List<FileDto2> list = dao.getList(dto);
        
        model.addAttribute("list", list);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("endPageNum", endPageNum);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("dto", dto);
        model.addAttribute("totalRow", totalRow);
        model.addAttribute("pageNum", pageNum);
    }
    
    @Override
    public void getDetail(Model model, int num) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public ResponseEntity<InputStreamResource> getFileData(int num) {
        // 다운로드 해줄 파일의 정보를 DB 에서 읽어온다.
        FileDto2 dto = dao.getData(num);
        ResponseEntity<InputStreamResource> responseEn = null;
        try {
            // 다운로드 시켜줄 원본 파일명
            String encodedName = URLEncoder.encode(dto.getOrgFileName(), "utf-8");
            // 파일명에 공백이 있는 경우 파일명이 이상해지는 걸 방지
            encodedName = encodedName.replaceAll("\\+"," ");
            // 응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
            HttpHeaders headers = new HttpHeaders();
            // 파일을 다운로드 시켜주겠다는 정보
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
            // 파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedName);
            // 파일의 크기 정보도 담아준다.
            headers.setContentLength(dto.getFileSize());
            // 읽어들일 파일의 경로 구성
            String filePath = fileLocation + File.separator + dto.getSaveFileName();
            // 파일에서 읽어들일 스트림 객체 생성
            InputStream is = new FileInputStream(filePath);
            // InputStreamResource
            InputStreamResource isr = new InputStreamResource(is);
            // InputStreamResource 객체를 얻어내서 지역변수에 담고
            responseEn = ResponseEntity.ok().headers(headers).body(isr);
        } catch (Exception e) {
            // 예외를 던지고 ExceptionController에서 처리할 수 있다.
            throw new RuntimeException("파일 다운로드 중에 예외가 발생했습니다.");
        }
        
        // InputStreamResource 객체를 리턴해준다.
        return responseEn;
    }
    
    @Override
    public void updateFile(Model model, int num) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void deleteFile(int num) {
        // TODO Auto-generated method stub
        
    }
    
}
