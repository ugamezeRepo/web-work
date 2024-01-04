package com.example.boot07.controller;

import java.io.File;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.boot07.dto.FileDto;

@Controller
public class FileController {

    @ResponseBody
    // 파일 업로드 요청 처리
    @PostMapping("/file/upload")
    public String upload(String title, MultipartFile myFile) {
        // 원본 파일명
        String orgFileName = myFile.getOriginalFilename();
        // 파일 크기
        long fileSize = myFile.getSize();
        // 저장할 파일의 이름 겹치지 않는 유일한 문자열로 얻어내기
        String saveFileName = UUID.randomUUID()
                .toString();
        // 저장할 파일의 전체 경로 구성
        String filePath =
                "C:\\acorn202310\\acorns\\acorn-web-work\\upload" + File.separator + saveFileName;
        try {
            File f = new File(filePath);
            myFile.transferTo(f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "upload Ok!";
    }

    @PostMapping("/file/upload2")
    public String upload(FileDto dto) {

        return "file/upload";
    }

    @GetMapping("/file/uploadform")
    public String uploadForm() {

        return "file/uploadform";
    }
}
