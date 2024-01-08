package com.example.boot09.service;

import java.io.File;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.boot09.dto.GalleryDto;
import com.example.boot09.repository.GalleryDao;

@Service
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private GalleryDao dao;

    @Value("${file.location}")
    private String fileLocation;

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

    }

    @Override
    public void selectAll(Model model, int pageNum) {

    }

}
