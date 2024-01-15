package com.example.boot09.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("fileDto2")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileDto2 {
    private int num;
    private String writer; // 로그인된 사용자 아이디
    private String title;
    private MultipartFile myFile;
    private String orgFileName;
    private String saveFileName;
    private long fileSize;
    private String regdate;
    private int startRowNum;
    private int endRowNum;
    private String condition = "";
    private String keyword = "";
    private int pageNum = 1;

}
